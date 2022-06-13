package com.th.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysMeasure;
import com.th.system.service.SysMeasureService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;

@CrossOrigin
@RestController
@RequestMapping("/measure")
public class SysMeasureController {

	@Autowired
	private SysMeasureService sysMeasureService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	//查询 登录人管理项目下结构物的测点
	@GetMapping(value = "/findByUserMeasure", produces="application/json;charset=UTF-8")
	public String findByUserMeasure(HttpServletRequest request) {
		List<SysMeasure> list = sysMeasureService.findByUserId(request);
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	} 
	
}
