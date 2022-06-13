package com.th.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.th.system.dao.SysProjecAndModelMapper;
import com.th.system.po.SysModel;
import com.th.system.po.SysProjecAndModel;
import com.th.system.po.SysProject;
import com.th.system.po.SysUser;
import com.th.system.service.SysModelService;
import com.th.system.service.SysProjecAndModelService;
import com.th.system.service.SysProjectService;
import com.th.system.service.SysUserService;
import com.th.system.utils.EncapsulationUtil;
import com.th.system.utils.JsonUtil;
import com.th.system.utils.MD5;
import org.springframework.transaction.annotation.Transactional;

@Service("SysProjecAndModelServiceImpl")
@Transactional
public class SysProjecAndModelServiceImpl implements SysProjecAndModelService{

	@Autowired
	private SysProjecAndModelMapper sysProjecAndModelMapper;
	
	@Autowired
	private SysModelService sysModelService;
	
	@Autowired
	private SysProjectService sysProjectService;
	
	@Autowired
	private SysUserService sysUserService;
	
	EncapsulationUtil en = new EncapsulationUtil();
	
	@Override
	public Integer insert(SysProjecAndModel sysProjecAndModel) {
		return sysProjecAndModelMapper.insert(sysProjecAndModel);
	}

	@Override
	public Integer delect(Integer projectId, Integer modelId) {
		// TODO Auto-generated method stub
		return sysProjecAndModelMapper.delect(projectId, modelId);
	}

	@Override
	public List<SysProjecAndModel> findBySysProjecId(Integer projectId) {
		// TODO Auto-generated method stub
		return sysProjecAndModelMapper.findBySysProjecId(projectId);
	}

	@Override
	public List<SysModel> findBySysProjecIdPro(Integer projectId) {
		// TODO Auto-generated method stub
		return sysProjecAndModelMapper.findBySysProjecIdPro(projectId);
	}

	@Override
	public JSONObject updateProjec(String modelIdList, String projectName, String userName, String password,
			Integer projectId, Integer userId) {
		List<SysProjecAndModel> list = sysProjecAndModelMapper.findBySysProjecId(projectId);
		String[] arr = modelIdList.split("@");
		for (int i = 0; i < list.size(); i++) {
			SysModel model = new SysModel();
			model.setModelId(list.get(i).getModelId());
			model.setIsBinding("1");
			sysModelService.updateMoele(model);
		}
		sysProjecAndModelMapper.delect(projectId, null);
		Integer result = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != "" || !"".equals(arr[i])) {
				SysProjecAndModel sysProjecAndModel = new SysProjecAndModel();
				sysProjecAndModel.setModelId(Integer.parseInt(arr[i]));
				sysProjecAndModel.setProjectId(projectId);		
				result = sysProjecAndModelMapper.insert(sysProjecAndModel);
				SysModel model = new SysModel();
				model.setModelId(Integer.parseInt(arr[i]));
				model.setIsBinding("2");
				sysModelService.updateMoele(model);
			}
		}
		this.updateUser(userId,userName, password);
		this.updateProject(projectName,projectId);
		JSONObject json = en.encapsulationStrJson(result, result);
		return json;
	}
	
	public void updateUser(Integer userId,String userName,String password) {
		SysUser user = new SysUser();
		user.setLoginName(userName);
		user.setUserName(userName);
		user.setPassword(MD5.getMD5(password));
		user.setUserId(userId);
		sysUserService.updateUser(user);
	}

	public void updateProject(String projectName,Integer projectId) {
		SysProject project = new SysProject();
		project.setProjectName(projectName);
		project.setProjectId(projectId);
		sysProjectService.updateProject(project);
	}

	@Override
	public JSONObject insertProjec(String projectName,Integer userId,Integer type) {
//		String projectId = UUID.randomUUID().toString().replace("-", "");
//		String userid = UUID.randomUUID().toString().replace("-", "");
//		String[] split = modelIdList.split("@");
//		Integer result = 0;
//		for (int i = 0; i < split.length; i++) {
//			if(split[i] != "" || !"".equals(split[i])) {
//				SysProjecAndModel sysProjecAndModel = new SysProjecAndModel();
//				sysProjecAndModel.setModelId(Integer.parseInt(split[i]));
////				sysProjecAndModel.setProjectId(projectId);		
//				result = sysProjecAndModelMapper.insert(sysProjecAndModel);
//				SysModel model = new SysModel();
//				model.setModelId(Integer.parseInt(split[i]));
//				model.setIsBinding("2");
//				sysModelService.updateMoele(model);
//			}
//		}
////		this.insertUserAndProject1(userid,userName, password, projectId);
		Integer count = this.insertProject(projectName,userId,type);
		JSONObject json = en.encapsulationStrJson(count,count);
		return json;
	}

	public Integer insertProject(String projectName,Integer userid,Integer type) {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataTime = format.format(date);
		SysProject project = new SysProject();
		project.setProjectName(projectName);
		project.setTime(dataTime);
		project.setCreationTime(dataTime);
		project.setUserId(userid);
		project.setShow("2");
		project.setType(type);
		Integer count = sysProjectService.insertProject(project);
		return count;
	} 

	public String insertUserAndProject1(Integer userid,String userName,String password,Integer projectId) {
		//新增用户
		SysUser user = new SysUser();
		user.setUserId(userid);
		user.setLoginName(userName);
		user.setUserName(userName);
		user.setPassword(MD5.getMD5(password));
		user.setRole("2");
		user.setForbidden("1");
		user.setIsMailbox("1");
		user.setIsNote("1");
		user.setNoDisturbing("1");
		sysUserService.insertUser(user);
		//添加到project表中
		SysProject project = new SysProject();
		project.setProjectId(projectId);
		project.setUserId(userid);
		sysProjectService.updateProject(project);
		return JsonUtil.writeAsString(null);
	}

	@Override
	public JSONObject insertPro(String modelIdList, Integer projectId) {
		String[] split = modelIdList.split(",");
		Integer result = 0;
		for (int i = 0; i < split.length; i++) {
			SysProjecAndModel sysProjecAndModel = new SysProjecAndModel();
			sysProjecAndModel.setModelId(Integer.parseInt(split[i]));
			sysProjecAndModel.setProjectId(projectId);		
			result = sysProjecAndModelMapper.insert(sysProjecAndModel);
			if(result == 1) {
				SysModel model = new SysModel();
				model.setModelId(Integer.parseInt(split[i]));
				model.setIsBinding("2");
				sysModelService.updateMoele(model);
			}
		}
		JSONObject json = en.encapsulationStrJson(result, result);
		return json;
	}

	@Override
	public JSONObject updateProjecPro(String projectName, Integer projectId, String userId,Integer type) {
		SysProject project = new SysProject();
		project.setProjectId(projectId);
		if(userId != null && !"".equals(userId)) {
			project.setUserId(Integer.valueOf(userId));
		}
		project.setProjectName(projectName);
		project.setType(type);
		Integer result = sysProjectService.updateProject(project);
		JSONObject json = en.encapsulationStrJson(result, result);
		return json;
	}
	
}
