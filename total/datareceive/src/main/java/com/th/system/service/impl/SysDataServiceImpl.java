package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysDataMapper;
import com.th.system.po.SysData;
import com.th.system.po.SysEquipment;
import com.th.system.po.SysThresholdValue;
import com.th.system.service.SysCautionService;
import com.th.system.service.SysDataService;
import com.th.system.service.SysEquipmentService;
import com.th.system.service.SysThresholdValueService;
import com.th.system.service.SysUserService;
import com.th.system.utils.AnalysisUtil;
import com.th.system.utils.SendMail;
import com.th.system.utils.SendText;
import com.th.system.vo.SysCautionVo;
import com.th.system.vo.SysProjectUserVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysDataServiceImpl")
@Transactional
public class SysDataServiceImpl implements SysDataService{

	@Autowired
	private SysDataMapper sysDataMapper;
	
	@Autowired
	private SysEquipmentService sysEquipmentService;
	
	@Autowired
	private SysThresholdValueService sysThresholdValueService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysCautionService sysCautionService;
	
	AnalysisUtil util = new AnalysisUtil();
	
	@Override
	public Integer insertTextData(String data, String time) {
		return sysDataMapper.insertTextData(data,time);
	}
	
	@Override
	public Integer findDataCount() {
		// TODO Auto-generated method stub
		return sysDataMapper.findDataCount();
	}
	
	@Override
	public Integer insertData(Integer equipmentId, Integer modelId, String data, String specification, String time,
			String hour) {
		SysData sysData = new SysData();
		sysData.setEquipmentId(equipmentId);
		sysData.setModelId(modelId);
		sysData.setData(data);
		sysData.setSpecification(specification);
		sysData.setTime(time);
		sysData.setHour(hour);
		String[] strings ={"应变计","倾斜角","噪声","PM2","PM2","温度","湿度","光照"};
		return sysDataMapper.insertData(sysData);
	}

	@Override
	public SysData findByEquipment(Integer equipmentId) {
		return sysDataMapper.findByEquipment(equipmentId);
	}

	@Override
	public void showData(String instruct) {
		if(util.contains(instruct)) {
			String number = util.split(instruct, 12, 28);
			String data = util.split(instruct, 40, 44);
			double covert = util.covert(data);
			SysEquipment equipment = new SysEquipment();
			equipment = sysEquipmentService.findByNumber(number);
			if(equipment == null) {
				equipment = sysEquipmentService.findByNumberPro(number);
			}
			if(equipment != null) {
				String time = util.getTimeAll();
				String hour = util.getHour();
				Integer equipmentId = equipment.getEquipmentId();
				this.insertData(equipmentId, equipment.getModelId(), covert+"", number, time, hour);
				this.thread(equipmentId, covert+"");
			}
		}

	}
	
	public void thread(Integer equipmentId,String data) {
		String content = "";
		String caution = "";
		List<SysThresholdValue> oneWarning = sysThresholdValueService.oneWarning(data,equipmentId);
		if(oneWarning.size() != 0) {
			caution = "1";
			content = "一级警告";
		}else {
			List<SysThresholdValue> twoWarning = sysThresholdValueService.twoWarning(data, equipmentId);
			if(twoWarning.size() != 0) {
				caution = "2";
				content = "二级警告";
			}else {
				List<SysThresholdValue> threeWarning = sysThresholdValueService.threeWarning(data, equipmentId);
				if(threeWarning.size() != 0) {
					caution = "3";
					content = "三级警告";
				}
			}
		}
		if(content != "") {
			String time = util.getTimeAll();
			SysProjectUserVo user = sysUserService.findEquipmentUser(equipmentId);
			SysCautionVo cautionVo = sysUserService.findByEquipmentId(equipmentId,caution);
			
			String contents = "设备:"+user.getEquipmentName()+" 型号:"+user.getNumber()+" 数值:"+data;
			
			
			sysCautionService.insertCaution(user.getModelId(), user.getMeasureId(), caution, contents, time, time);
//			if(user != null &&(user.getMailbox()!=null||user.getPhoneNumber()!=null)) {
//				if(user.getIsMailbox().equals("1")) {
//					SendMail mail = new SendMail();
//					try {
//						String text = content+" 项目:"+user.getProjectName()+" 结构物名:"+user.getModelName()+" 测点名:"+user.getMeasureName()
//						+"设备:"+user.getEquipmentName()+" 数值:"+data;
//						mail.goMailbox(user.getMailbox(), text);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				if(user.getIsNote().equals("1")) {
//					 //发送短信66666	
//				}
//			}
			if(cautionVo != null && (cautionVo.getMailbox() != null || cautionVo.getPhoneNumber() != null)) {
				if(cautionVo.getIsUserMailbox().equals("1") && cautionVo.getIsMailbox().equals("true")) {
					SendMail mail = new SendMail();
					try {
						String text = content+" 项目:"+user.getProjectName()+" 结构物名:"+user.getModelName()+" 测点名:"+user.getMeasureName()
						+"设备:"+user.getEquipmentName()+" 数值:"+data;
						mail.goMailbox(user.getMailbox(), text);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(cautionVo.getIsNote().equals("true") && cautionVo.getIsUserMailbox().equals("1")) {
					String param = user.getPhoneNumber()+"|"+content+"|"+user.getProjectName()+"|"+user.getModelName()+"|"+user.getMeasureName()+"|"+user.getEquipmentName()+"|"+data;
					SendText text = new SendText();
					text.goSendText(param);
				}
			}
			
		}
		
	}
	
	@Override
	public SysData findHourData(Integer equipmentId, String time, String hour) {
		// TODO Auto-generated method stub
		return sysDataMapper.findHourData(equipmentId, time, hour);
	}

	
	
}
