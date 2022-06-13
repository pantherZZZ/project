package com.th.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.service.SysFocussingService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import com.th.system.utils.Weekutil;
import com.th.system.vo.SysFocussingVo;

@CrossOrigin
@RestController
@RequestMapping("/focussing")
public class SysFocussingController {

	@Autowired
	private SysFocussingService sysFocussingService;
	 
	EncapsulationUtil en = new EncapsulationUtil();
	
	//新增
	@PostMapping(value = "/insertFocussing", produces="application/json;charset=UTF-8")
	public String insertFocussing(HttpServletRequest request,Integer factorId,String focussingType,String focussingMode
			 ,String state,String daysStartTime,String daysEndTime,String weekStartTime
			 ,String weekEndTime,String monthStartTime,String monthEndTime) {
		String weekStart = null;
		String weekEnd = null;
		if(weekStartTime != "" && weekEndTime != "") {
			Weekutil week = new Weekutil();
			weekStart = week.week(weekStartTime);
			weekEnd = week.week(weekEndTime);
		}
		Integer result = sysFocussingService.insertFocussing(request, factorId, focussingType, focussingMode, state, daysStartTime, daysEndTime, weekStartTime, weekEndTime, monthStartTime, monthEndTime, weekStart,weekEnd);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	}

	//修改
	@PostMapping(value = "/updateFocussing", produces="application/json;charset=UTF-8")
	public String updateFocussing(HttpServletRequest request,Integer factorId,String focussingType,String focussingMode
			 ,String state,String daysStartTime,String daysEndTime,String weekStartTime
			 ,String weekEndTime,String monthStartTime,String monthEndTime,Integer focussingId) {
		String weekStart = null;
		String weekEnd = null;
		if(weekStartTime != "" && weekEndTime != "") {
			Weekutil week = new Weekutil();
			weekStart = week.week(weekStartTime);
			weekEnd = week.week(weekEndTime);
		}
		Integer result = sysFocussingService.updateFocussing(focussingId, factorId, focussingType, focussingMode, state, daysStartTime, daysEndTime, weekStartTime, weekEndTime, monthStartTime, monthEndTime, weekStart,weekEnd);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	}
	
	//删除
	@PostMapping(value = "/delFocussing", produces="application/json;charset=UTF-8")
	public String delFocussing(HttpServletRequest request,Integer focussingId) {
		Integer result = sysFocussingService.delFocussing(focussingId);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	}
	
	//按照登录人查询
	@PostMapping(value = "/findUserFocussing", produces="application/json;charset=UTF-8")
	public String findUserFocussing(HttpServletRequest request,String page,String limit) {
		List<SysFocussingVo> list = sysFocussingService.findUserFocussing(request, page, limit);
		List<SysFocussingVo> list2 = sysFocussingService.findUserFocussing(request, "1", "10000000");
		JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
		return JsonUtil.writeAsString(json);
	}

	//按照登录人查询
	@PostMapping(value = "/findFocussing", produces="application/json;charset=UTF-8")
	public String findFocussing(HttpServletRequest request,Integer focussingId) {
		SysFocussingVo focussing = sysFocussingService.findFocussing(focussingId);
		JSONObject message =new JSONObject();
		JSONObject data =new JSONObject();
		data.put("list",focussing);
		data.put("code",200);
		message.put("data", data);
		message.put("status", 200);
		message.put("message", "成功");
		return JsonUtil.writeAsString(message);
	}
}
