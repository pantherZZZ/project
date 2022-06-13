package com.th.system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysType;
import com.th.system.service.SysTypeService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class SysTypeController extends HttpClient {

	@Autowired
	private SysTypeService sysTypeService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	//查询所有类型
	@PostMapping(value = "/findTypeAll", produces="application/json;charset=UTF-8")
	public String findAll(HttpServletRequest request,Integer plantId) throws IOException {
		List<SysType> list = sysTypeService.findTypeAll(plantId);
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	} 
	
}
