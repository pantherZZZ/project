package com.th.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysProject;
import com.th.system.po.SysUser;
import com.th.system.service.SysCautionTacticsSerice;
import com.th.system.service.SysModelService;
import com.th.system.service.SysProjectService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import com.th.system.vo.SysCautionTacticsVo;
import com.th.system.vo.SysModelVo;
import com.th.system.vo.SysProjectVo;

@CrossOrigin
@RestController
@RequestMapping("/cautionTactics")
public class SysCautionTacticsController {

	@Autowired
	private SysCautionTacticsSerice sysCautionTacticsSerice;
	
	@Autowired
	private SysModelService sysModelService;
	
	@Autowired
	private SysProjectService sysProjectService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	//查询所有用户
	@PostMapping(value = "/findUserList", produces="application/json;charset=UTF-8")
	public String findUserList(HttpServletRequest request,Integer projectId) {
		List<SysUser> list = sysProjectService.findByProjectIdAir(projectId);
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
	//根据userid查询 结构物
	@PostMapping(value = "/findByUserModel", produces="application/json;charset=UTF-8")
	public String findByUserModel(HttpServletRequest request,Integer projectId) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		String role = "1";//(String) session.getAttribute("role");
		Integer user = null;
		if(!role.equals("1")) {
			user = userid;
		}
		List<SysModelVo> list = sysModelService.findByProjectIdAir(user,0,0, null,null);
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
	//根据当前登录人管理的项目(展示项目)
	@PostMapping(value = "/showProject", produces="application/json;charset=UTF-8")
	public String showProject(HttpServletRequest request,String type) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		String role = (String) session.getAttribute("role");
		Integer useridPro = userid;
		if(role.equals("1")) {
			useridPro = null;
		}

		List<SysProject> list = sysProjectService.findByUseridPro(useridPro,0,0,null,null,Integer.parseInt(type));
		List<SysProjectVo> data = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<SysModelVo> modelList = sysModelService.findByProjectId(list.get(i).getProjectId(),0,0,null,null);
			String val = "";
			for (int j = 0; j < modelList.size(); j++) {
				if(j == 0) {
					val += modelList.get(j).getModelName();
				}else {
					val += ","+modelList.get(j).getModelName();
				}
			}
			SysProjectVo project = new SysProjectVo();
			project.setProjectId(list.get(i).getProjectId());
			project.setProjectName(list.get(i).getProjectName());
			project.setModelName(val);
			project.setTime(list.get(i).getTime());
			project.setUserId(userid);
			project.setType(list.get(i).getType());
			data.add(project);
		}
		JSONObject json = en.encapsulationListJson(list);
		return JsonUtil.writeAsString(json);
	}
	
	//新增告警策略
	@PostMapping(value = "/insertCautionTactics", produces="application/json;charset=UTF-8")
	public String insertCautionTactics(HttpServletRequest request,Integer userId,Integer projectId
			,Integer modelId,String cautionType,String isMailbox,String isNote
			,String cautionGrade,String forbidden) {
		Integer result = sysCautionTacticsSerice.insertCautionTactics(userId, projectId, modelId, cautionType, isMailbox, isNote, cautionGrade, forbidden);
		JSONObject json = en.encapsulationStrJson(result,result);
		return JsonUtil.writeAsString(json);
	} 
	
	//修改告警策略
	@PostMapping(value = "/updateCautionTactics", produces="application/json;charset=UTF-8")
	public String updateCautionTactics(HttpServletRequest request,Integer userId,Integer projectId
			,Integer modelId,String cautionType,String isMailbox,String isNote
			,String cautionGrade,String forbidden,Integer cautionTacticsId) {
		Integer result = sysCautionTacticsSerice.updateCautionTactics(cautionTacticsId, userId, projectId, modelId, cautionType, isMailbox, isNote, cautionGrade, forbidden);
		JSONObject json = en.encapsulationStrJson(result,result);
		return JsonUtil.writeAsString(json);
	} 

	//删除告警策略
	@PostMapping(value = "/delCautionTactics", produces="application/json;charset=UTF-8")
	public String delCautionTactics(HttpServletRequest request,Integer cautionTacticsId) {
		Integer result = sysCautionTacticsSerice.delCautionTactics(cautionTacticsId);
		JSONObject json = en.encapsulationStrJson(result,result);
		return JsonUtil.writeAsString(json);
	}
	
	//按照id 查询警告策略
	@PostMapping(value = "/findById", produces="application/json;charset=UTF-8")
	public String findById(HttpServletRequest request,Integer cautionTacticsId) {
		SysCautionTacticsVo cautionTactics = sysCautionTacticsSerice.findById(cautionTacticsId);
		JSONObject json = en.encapsulationObjJson(cautionTactics);
		return JsonUtil.writeAsString(json);
	}
	

	//按照登录人id 查询警告策略
	@PostMapping(value = "/findUserCautionTactics", produces="application/json;charset=UTF-8")
	public String findUserCautionTactics(HttpServletRequest request,String page,String limit,String userName,String modelName,String cautionGrade) {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		String role = "1";//(String) session.getAttribute("role");
		Integer useridPro = userid;
		if(role.equals("1")) {
			useridPro = null;
		}
		int pages = Integer.parseInt(page)-1;
		List<SysCautionTacticsVo> cautionTactics = sysCautionTacticsSerice.findUserCautionTactics(useridPro, pages, Integer.parseInt(limit), userName, modelName, cautionGrade);
		JSONObject json = en.encapsulationListJson(cautionTactics);
		return JsonUtil.writeAsString(json);
	}
}
