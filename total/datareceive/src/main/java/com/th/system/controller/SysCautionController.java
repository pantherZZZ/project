package com.th.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.service.SysCautionService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.HttpClient;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysCantionVo;
@CrossOrigin
@RestController
@RequestMapping("/caution")
public class SysCautionController extends HttpClient{
    
	@Autowired
	private SysCautionService sysCautionService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	//新增告警信息
	@PostMapping(value = "/insertCaution", produces="application/json;charset=UTF-8")
	public String insertCaution(HttpServletRequest request,Integer modelId,Integer cautionSource,String cautionGrade
			,String cautionMessage) {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataTime = format.format(date);
		Integer result = sysCautionService.insertCaution(modelId, cautionSource, cautionGrade, cautionMessage, dataTime, dataTime);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	} 
	
	//告警信息处理
	@PostMapping(value = "/updateCaution", produces="application/json;charset=UTF-8")
	public String updateCaution(HttpServletRequest request,Integer cautionId) {
		Integer result = sysCautionService.updateCaution(cautionId);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	} 
	
	//告警信息删除
	@PostMapping(value = "/delCantion", produces="application/json;charset=UTF-8")
	public String delCantion(HttpServletRequest request,Integer cautionId) {
		Integer result = sysCautionService.delCantion(cautionId);
		JSONObject json = en.encapsulationStrJson(result, result);
		return JsonUtil.writeAsString(json);
	}
	
	//按照登录人查询警告信息
	@PostMapping(value = "/findByUserCantion", produces="application/json;charset=UTF-8")
	public String findByUserCantion(HttpServletRequest request,String page,
			String limit,String modelName,String measureName,String cautionGrade) {
		HttpSession session = request.getSession();
		int userid = (int) session.getAttribute("userid");
		String role = "1";//(String) session.getAttribute("role");
		Integer useridPro = userid;
		if(role.equals("1")) {
			useridPro = null;
		}
		int pages = Integer.parseInt(page)-1;
		List<SysCantionVo> list = sysCautionService.findByUserCantion(useridPro, pages, Integer.parseInt(limit), modelName, measureName, cautionGrade);
		List<SysCantionVo> list2 = sysCautionService.findByUserCantion(useridPro, 0,1000000, modelName, measureName, cautionGrade);
		JSONObject json = en.encapsulationlimitJson(list, page, limit, list2.size());
		return JsonUtil.writeAsString(json);
	}
	
}
