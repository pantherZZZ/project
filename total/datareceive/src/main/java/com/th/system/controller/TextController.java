package com.th.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysEquipment;
import com.th.system.service.SysEquipmentService;
import com.th.system.service.SysUserService;
import com.th.system.utils.AnalysisUtil;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysCautionVo;

@CrossOrigin
@RestController
@RequestMapping("/text")
public class TextController {
	
	@Autowired
	private SysEquipmentService sysEquipmentService;
	
	@Autowired
	private SysUserService sysUserService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	//查询所有类型
	@PostMapping(value = "/text", produces="application/json;charset=UTF-8")
	public String findAll(HttpServletRequest request,String str) throws IOException {
//		JSONArray json = new JSONArray();
//		JSONObject o = new JSONObject();
//		aa a = new aa();
//		a.setId("5fd97ee46decb9617960fb9b");
//		a.setV("0");
//		a.setAlwaysShow(true);
//		a.setComponent("Layout");
//		a.setDate("2020-12-16T03:28:36.891Z");
//		a.setHidden(false);
//		a.setName("");
//		a.setPath("/system");
//		a.setRedirect("");
//		a.setSort("1");
//		a.setSystem("1");
//		a.setTitle("系统管理");
//		 Map<String,Object> map = new HashMap<String, Object>();
//		 map.put("title", "系统管理");
//		 map.put("icon", "el-icon-star-on");
//		 map.put("noCache", true);
//		 a.setMeta(map);
//		JSONObject json1 = new JSONObject();
//		json1.put("title","系统管理");
//		json1.put("icon","el-icon-star-on");
//		json1.put("noCache",false);
//			aa a1 = new aa();
//			a1.setId("5fd97ee46decb9617960fb9b");
//			a1.setV("0");
//			a1.setComponent("system/menu/index");
//			a1.setDate("2020-12-16T03:28:36.891Z");
//			a1.setHidden(false);
//			a1.setName("");
//			a1.setPath("/system");
//			a1.setRedirect("");
//			a1.setSort("1");
//			a1.setSystem("1");
//			a1.setTitle("菜单管理");
//			Map<String,Object> map1 = new HashMap<String, Object>();
//			map1.put("title", "菜单管理");
//			map1.put("icon", "el-icon-star-on");
//			map1.put("noCache", true);
//			a1.setMeta(map1);
//			o.put("children", a1);
//			aa a2 = new aa();
//			a2.setId("5fd97ee46decb9617960fb9b");
//			a2.setV("0");
//			a2.setComponent("system/menu/index");
//			a2.setDate("2020-12-16T03:28:36.891Z");
//			a2.setHidden(false);
//			a2.setName("");
//			a2.setPath("/system");
//			a2.setRedirect("");
//			a2.setSort("1");
//			a2.setSystem("1");
//			a2.setTitle("路由缓存");
//			Map<String,Object> map2 = new HashMap<String, Object>();
//			map2.put("title", "路由缓存");
//			map2.put("icon", "el-icon-star-on");
//			map2.put("noCache", true);
//			a2.setMeta(map2);
//			json.add(a);
//			JSONArray json3 = new JSONArray();
//			json3.add(a1);
//			json3.add(a2);
//			o.put("children", json3);
//			json.add(o);
			JSONObject json2 = en.json(null);
		return JsonUtil.writeAsString(json2);
	} 

	
}
