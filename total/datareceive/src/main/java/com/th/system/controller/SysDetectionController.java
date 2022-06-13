package com.th.system.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysDetection;
import com.th.system.po.SysFactor;
import com.th.system.po.SysModelNumber;
import com.th.system.po.SysUnits;
import com.th.system.service.SysDetectionService;
import com.th.system.service.SysFactorService;
import com.th.system.service.SysModelNumberService;
import com.th.system.service.SysUnitsService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysDetectionVo;

@CrossOrigin
@RestController
@RequestMapping("/detection")
public class SysDetectionController extends HttpClient {

	@Autowired
	private SysDetectionService sysDetectionService;
	
	@Autowired
	private SysUnitsService sysUnitsService;
	
	@Autowired
	private SysModelNumberService sysModelNumberService;
	
	@Autowired
	private SysFactorService sysFactorService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	@PostMapping(value = "/factorModelId", produces="application/json;charset=UTF-8")
	public String factorModelId(HttpServletRequest request,Integer modelId) {
		List<SysFactor> list = sysFactorService.findByModelId(modelId);
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
	@PostMapping(value = "/insertSysModelNumber", produces="application/json;charset=UTF-8")
	public String insertSysModelNumber(HttpServletRequest request,String modelNumberName) {
//		String modelNumberId = UUID.randomUUID().toString().replace("-", "");
		Integer number = sysModelNumberService.insertSysModelNumber(null, modelNumberName);
		JSONObject json = en.encapsulationStrJson(number, number);
		return JsonUtil.writeAsString(json);
	}
	
	@PostMapping(value = "/findModelNumberAll", produces="application/json;charset=UTF-8")
	public String findModelNumberAll(HttpServletRequest request,Integer typeId) {
		List<SysModelNumber> list = sysModelNumberService.findAll(typeId);
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
    @PostMapping(value = "/delDetection", produces="application/json;charset=UTF-8")
	public String delDetection(HttpServletRequest request,Integer detectionId) {
    	Integer count = sysDetectionService.delDetection(detectionId);
		JSONObject json = en.encapsulationStrJson(count, count);
		return JsonUtil.writeAsString(json);
	}
	
    @PostMapping(value = "/findByUserDetection", produces="application/json;charset=UTF-8")
	public String findByUserDetection(HttpServletRequest request,String page,String limit,String detectionName) {
    	int code = httpClientPost(request);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
    		int pages = Integer.parseInt(page)-1;
        	HttpSession session = request.getSession();
        	Integer userid = (Integer) session.getAttribute("userid");
    		List<SysDetectionVo>  list = sysDetectionService.findByUserId(userid, detectionName, pages, Integer.parseInt(limit));
    		List<SysDetectionVo>  list2 = sysDetectionService.findByUserId(userid, detectionName, 0,10000000);
    		JSONArray arr =new JSONArray();
    		for (int i = 0; i < list.size(); i++) {
    			JSONObject data1 =new JSONObject();
    			JSONArray data2 =new JSONArray();
    			data1.put("detection", list.get(i));
				List<SysUnits> unitList = sysUnitsService.findByfactorId(list.get(i).getFactorId());
				data2.add(unitList);
				data1.put("list", data2);
				arr.add(data1);
    		}
    		data.put("data",arr);
    		data.put("total",list2.size());
    		data.put("page",page);
    		data.put("limit",limit);
    		data.put("code",code);
    		message.put("data", data);
    		message.put("status", 200);
    		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
    
    @PostMapping(value = "/findByDetectionId", produces="application/json;charset=UTF-8")
	public String findByDetectionId(HttpServletRequest request,Integer detectionId) {
    	SysDetection detection = sysDetectionService.findById(detectionId);
		int code = httpClientPost(request);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("code",code);
		data.put("list",detection);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
    
    @PostMapping(value = "/updateDetection", produces="application/json;charset=UTF-8")
  	public String updateDetection(HttpServletRequest request,Integer detectionId,String detectionName,
  			Integer factorId,Integer detectionTypeId) {
		Integer count = sysDetectionService.updateDetection(detectionId, detectionName, factorId, detectionTypeId);
		JSONObject json = en.encapsulationStrJson(count, count);
  		return JsonUtil.writeAsString(json);
  	}
   
    @PostMapping(value = "/insertDetection", produces="application/json;charset=UTF-8")
  	public String insertDetection(HttpServletRequest request,String detectionName,
  			Integer factorId,Integer detectionTypeId) {
    	HttpSession session = request.getSession();
    	Integer userId = (Integer) session.getAttribute("userid");
		Integer count = sysDetectionService.insertDetection(detectionName, factorId, detectionTypeId, userId);
		JSONObject json = en.encapsulationStrJson(count, count);
  		return JsonUtil.writeAsString(json);
  	}
    
    @PostMapping(value = "/findByfactorId", produces="application/json;charset=UTF-8")
  	public String findByfactorId(HttpServletRequest request,Integer factorId) {
    	List<SysUnits> list = sysUnitsService.findByfactorId(factorId);
  		JSONObject json = en.encapsulationListJson(list);
  		return JsonUtil.writeAsString(json);
  	}
}
