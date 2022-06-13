package com.th.system.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysThresholdValue;
import com.th.system.service.SysThresholdValueService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysThresholdValueVo;

@CrossOrigin
@RestController
@RequestMapping("/threshold")
public class SysThresholdValueController extends HttpClient  {

	@Autowired
	private SysThresholdValueService sysThresholdValueService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	//阀值按照 modelId模型id查询
	@PostMapping(value = "/findThresholdValue",produces="application/json;charset=UTF-8")
	public String findThresholdValue(Integer modelId,String page,String limit,String measureName) {
		String thresholdValue = sysThresholdValueService.findThresholdValue(modelId, page, limit, measureName);
		return thresholdValue;
	} 
	
	//清空
	@PostMapping(value = "/delThrescholdValue",produces="application/json;charset=UTF-8")
	public String delThresholdVacclue(HttpServletRequest request,Integer thresholdValueId) {
		SysThresholdValue thresholdValue = new SysThresholdValueVo();
		thresholdValue.setMaximum1("");
		thresholdValue.setMaximum2("");
		thresholdValue.setMaximum3("");
		thresholdValue.setMinimum1("");
		thresholdValue.setMinimum2("");
		thresholdValue.setMinimum3("");
		thresholdValue.setShow("2");
		thresholdValue.setThresholdValueId(thresholdValueId);
		Integer result = sysThresholdValueService.updateThreshold(thresholdValue);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	} 
	
	//修改阀值
	@PostMapping(value = "/updateThreshold", produces="application/json;charset=UTF-8")
	public String updateThreshold(HttpServletRequest request,Integer thresholdValueId,
			Integer measureId,String thresholdValue1,String thresholdValue2,String thresholdValue3) {
		int result = sysThresholdValueService.insertThresholdPro(request, thresholdValueId, measureId, thresholdValue1, thresholdValue2, thresholdValue3);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	} 
	
	//id查询
	@PostMapping(value = "/findByThresholdValueId",produces="application/json;charset=UTF-8")
	public String findByThresholdValueId(Integer thresholdValueId) {
		String thresholdValue = sysThresholdValueService.findByThresholdValueId(thresholdValueId);
		return thresholdValue;
	}
	
}
