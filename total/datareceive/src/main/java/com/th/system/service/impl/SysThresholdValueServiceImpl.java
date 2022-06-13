package com.th.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.th.system.dao.SysThresholdValueMapper;
import com.th.system.po.SysThresholdValue;
import com.th.system.service.SysThresholdValueService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysThresholdValueVo;
import com.th.system.vo.ThresholdValueVo;
import org.springframework.transaction.annotation.Transactional;

@Service("SysThresholdValueServiceImpl")
@Transactional
public class SysThresholdValueServiceImpl implements SysThresholdValueService{

	@Autowired
	private SysThresholdValueMapper sysThresholdValueMapper;

	EncapsulationUtil en = new EncapsulationUtil();
	
	@Override
	public Integer updateThreshold(SysThresholdValue sysThresholdValue) {
		// TODO Auto-generated method stub
		return sysThresholdValueMapper.updateThreshold(sysThresholdValue);
	}

	@Override
	public Integer insertThreshold(SysThresholdValue sysThresholdValue) {
		// TODO Auto-generated method stub
		return sysThresholdValueMapper.insertThreshold(sysThresholdValue);
	}

	@Override
	public List<SysThresholdValue> oneWarning(String value,Integer equipmentId) {
		// TODO Auto-generated method stub
		return sysThresholdValueMapper.oneWarning(value,equipmentId);
	}

	@Override
	public List<SysThresholdValue> twoWarning(String value,Integer equipmentId) {
		// TODO Auto-generated method stub
		return sysThresholdValueMapper.twoWarning(value,equipmentId);
	}

	@Override
	public List<SysThresholdValue> threeWarning(String value,Integer equipmentId) {
		// TODO Auto-generated method stub
		return sysThresholdValueMapper.threeWarning(value,equipmentId);
	}

	@Override
	public Integer delThresholdValue(Integer thresholdValueId) {
		// TODO Auto-generated method stub
		return sysThresholdValueMapper.delThresholdValue(thresholdValueId);
	}

	@Override
	public String findByThresholdValueId(Integer thresholdValueId) {
		ThresholdValueVo thresholVo = new ThresholdValueVo();
		SysThresholdValueVo threshold = sysThresholdValueMapper.findByThresholdValueId(thresholdValueId);
		if(threshold.getMaximum1() != ""&&threshold.getMaximum1() != null) {
			String thresholdValue1 = threshold.getMinimum1()+","+threshold.getMaximum1();
			thresholVo.setThresholdValue1(thresholdValue1);
		}
		if(threshold.getMaximum2() != ""&&threshold.getMaximum2() != null) {
			String thresholdValue2 = threshold.getMinimum2()+","+threshold.getMaximum2();
			thresholVo.setThresholdValue2(thresholdValue2);
		}
		if(threshold.getMaximum3() != ""&&threshold.getMaximum3() != null) {
			String thresholdValue3 = threshold.getMinimum3()+","+threshold.getMaximum3();
			thresholVo.setThresholdValue3(thresholdValue3);
		}
		thresholVo.setFactorName(threshold.getFactorName());
		thresholVo.setFactorId(threshold.getFactorId());
		thresholVo.setMeasureId(threshold.getMeasureId());
		thresholVo.setMeasureName(threshold.getMeasureName());
		thresholVo.setModelId(threshold.getModelId());
		thresholVo.setThresholdValueId(threshold.getThresholdValueId());
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("list",thresholVo);
		data.put("code",200);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}

	@Override
	public List<SysThresholdValueVo> findThresholdValueModelId(Integer modelId, int page, int limit,
			String measureName) {
		// TODO Auto-generated method stub
		return sysThresholdValueMapper.findThresholdValueModelId(modelId, page, limit, measureName);
	}

	@Override
	public int insertThresholdPro(HttpServletRequest request, Integer thresholdValueId, Integer measureId,
			String thresholdValue1, String thresholdValue2, String thresholdValue3) {
		SysThresholdValue threshold = new SysThresholdValue();
		int code = 0;
		Integer count = 0;
		if(thresholdValue1 != "" && thresholdValue1 != null ) {
			String[] split1 = thresholdValue1.split("，");
			if(split1.length > 1 ) {
				code = 202;
			}
		}
		if(thresholdValue2 != "" && thresholdValue2 != null && code != 202) {
			String[] split2 = thresholdValue1.split("，");
			if(split2.length > 1 ) {
				code = 202;
			}
		}
		if(thresholdValue3 != "" && thresholdValue3 != null && code != 202) {
			String[] split3 = thresholdValue1.split("，");
			if(split3.length > 1 ) {
				code = 202;
			}
		}
		if(code == 0) {
			if(thresholdValue1 != "" && thresholdValue1 != null ) {
				String[] mum1 = thresholdValue1.split(",");
				threshold.setMaximum1(mum1[1]);
				threshold.setMinimum1(mum1[0]);
			}
			if(thresholdValue2 != "" && thresholdValue2 != null) {
				String[] mum2 = thresholdValue2.split(",");
				threshold.setMaximum2(mum2[1]); 
				threshold.setMinimum2(mum2[0]);
			}
			if(thresholdValue3 != "" && thresholdValue3 != null) {
				String[] mum3 = thresholdValue3.split(",");
				threshold.setMaximum3(mum3[1]);
				threshold.setMinimum3(mum3[0]);
			}
			threshold.setThresholdValueId(thresholdValueId);
			if(measureId != null) {
				threshold.setMeasureId(measureId);
			}
			count = sysThresholdValueMapper.updateThreshold(threshold);
			if(count != 0) {
				code = 200;
			}else {
				code = 201;
			}
		}
		return code;
	}

	@Override
	public String findThresholdValue(Integer modelId, String page, String limit, String measureName) {
		int pages = Integer.parseInt(page)-1;
		List<ThresholdValueVo> listVo = new ArrayList<ThresholdValueVo>();
		List<SysThresholdValueVo> list = sysThresholdValueMapper.findThresholdValueModelId(modelId, pages, Integer.parseInt(limit), measureName);
		List<SysThresholdValueVo> list2 = sysThresholdValueMapper.findThresholdValueModelId(modelId, 0, 1000000, measureName);
		for (int i = 0; i < list.size(); i++) {
			SysThresholdValueVo threshold = list.get(i);
			ThresholdValueVo thresholVo = new ThresholdValueVo();
			if(threshold.getMaximum1() != ""&&threshold.getMaximum1() != null) {
				String thresholdValue1 = threshold.getMinimum1()+","+threshold.getMaximum1();
				thresholVo.setThresholdValue1(thresholdValue1);
			}
			if(threshold.getMaximum2() != ""&&threshold.getMaximum2() != null) {
				String thresholdValue2 = threshold.getMinimum2()+","+threshold.getMaximum2();
				thresholVo.setThresholdValue2(thresholdValue2);
			}
			if(threshold.getMaximum3() != ""&&threshold.getMaximum3() != null) {
				String thresholdValue3 = threshold.getMinimum3()+","+threshold.getMaximum3();
				thresholVo.setThresholdValue3(thresholdValue3);
			}
			thresholVo.setFactorName(threshold.getFactorName());
			thresholVo.setMeasureId(threshold.getMeasureId());
			thresholVo.setMeasureName(threshold.getMeasureName());
			thresholVo.setModelId(threshold.getModelId());
			thresholVo.setFactorId(threshold.getFactorId());
			thresholVo.setThresholdValueId(threshold.getThresholdValueId());
			listVo.add(thresholVo);
		}
		JSONObject json = en.encapsulationlimitJson(listVo, page, limit, list2.size());
		return JsonUtil.writeAsString(json);
	}
	
}
