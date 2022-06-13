package com.th.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.dao.SysPlantMapper;
import com.th.system.service.SysPlantService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;

@CrossOrigin
@RestController
@RequestMapping("/plant")
public class SysPlantController extends HttpClient {

	@Autowired
	private SysPlantService sysPlantService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	//查询所有工厂
	@GetMapping(value = "/findAll", produces="application/json;charset=UTF-8")
	public String findAll() {
		List<SysPlantMapper> list = sysPlantService.findAll();
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	} 
	
}
