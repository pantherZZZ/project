package com.th.system.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysMeasureMapper;
import com.th.system.po.SysMeasure;
import com.th.system.po.SysThresholdValue;
import com.th.system.service.SysMeasureService;
import com.th.system.service.SysThresholdValueService;
import org.springframework.transaction.annotation.Transactional;

@Service("sysMeasureServiceImpl")
@Transactional
public class SysMeasureServiceImpl implements SysMeasureService{

	@Autowired
	private SysMeasureMapper sysMeasureMapper;
	
	@Autowired
	private SysThresholdValueService sysThresholdValueService;
	
	@Override
	public Integer insertMeasure(String measureName,Integer factorId,String dataSource,Integer equipmentId,
			String pictureSite,Integer modelId,String label) {
		SysMeasure measure = new SysMeasure();
//		String measureId = UUID.randomUUID().toString().replace("-", "");
//		measure.setMeasureId(measureId);
		measure.setMeasureName(measureName);
		measure.setFactorId(factorId);
		measure.setDataSource(dataSource);
		measure.setEquipmentId(equipmentId);
		measure.setPictureSite(pictureSite);
		measure.setModelId(modelId);
		measure.setLabel(label);
		sysMeasureMapper.insertMeasure(measure);
		String measureId = sysMeasureMapper.showMeasureId(measureName, factorId, dataSource, equipmentId, modelId, label, pictureSite);
		SysThresholdValue sysThresholdValue = new SysThresholdValue();
		sysThresholdValue.setModelId(modelId);
		sysThresholdValue.setEquipmentId(equipmentId);
		sysThresholdValue.setMeasureId(Integer.parseInt(measureId));
		sysThresholdValue.setMaximum1("");
		sysThresholdValue.setMaximum2("");
		sysThresholdValue.setMaximum3("");
		sysThresholdValue.setMinimum1("");
		sysThresholdValue.setMinimum2("");
		sysThresholdValue.setMinimum3("");
		return sysThresholdValueService.insertThreshold(sysThresholdValue);
	}

	@Override
	public List<SysMeasure> findByModelId(Integer modelId,String measureName,int page,int limit) {
		// TODO Auto-generated method stub
		return sysMeasureMapper.findByModelId(modelId,measureName,page,limit);
	}

	@Override
	public SysMeasure findByMeasure(Integer measureId) {
		// TODO Auto-generated method stub
		return sysMeasureMapper.findByMeasure(measureId);
	}

	@Override
	public Integer updateMeasure(Integer measureId,String measureName,Integer factorId,String dataSource,Integer equipmentId,String pictureSite,Integer modelId,String label) {
		// TODO Auto-generated method stub
		SysMeasure measure = new SysMeasure();
		measure.setModelId(modelId);
		measure.setMeasureId(measureId);
		measure.setMeasureName(measureName);
		measure.setFactorId(factorId);
		measure.setDataSource(dataSource);
		measure.setEquipmentId(equipmentId);
		measure.setPictureSite(pictureSite);
		measure.setMeasureId(measureId);
		measure.setLabel(label);
		return sysMeasureMapper.updateMeasure(measure);
	}

	@Override
	public Integer dalMeasure(Integer measureId) {
		// TODO Auto-generated method stub
		return sysMeasureMapper.dalMeasure(measureId);
	}

	@Override
	public List<SysMeasure> findByUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		String role = "1";//(String) session.getAttribute("role");
		Integer useridPro = userid;
		if(role.equals("1")) {
			useridPro = null;
		}
		// TODO Auto-generated method stub
		return sysMeasureMapper.findByUserId(useridPro);
	}

	@Override
	public List<SysMeasure> findByfactorId(HttpServletRequest request,Integer factorId) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		String role = (String) session.getAttribute("role");
		Integer useridPro = userid;
		if(role.equals("1")) {
			useridPro = null;
		}
		return sysMeasureMapper.findByfactorId(useridPro, factorId);
	}

	@Override
	public String findByMeasurePro(Integer measureId, String time, String hour) {
		// TODO Auto-generated method stub
		return sysMeasureMapper.findByMeasurePro(measureId, time, hour);
	}

}
