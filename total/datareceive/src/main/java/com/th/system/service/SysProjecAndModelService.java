package com.th.system.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.th.system.po.SysModel;
import com.th.system.po.SysProjecAndModel;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysProjecAndModelService {
	public JSONObject updateProjecPro(String projectName,Integer projectId,String userId,Integer type);
	//新增关联表
	public Integer insert(SysProjecAndModel sysProjecAndModel);
	
	//根据modelId 或者 projectId删除
	public Integer delect(Integer projectId,Integer modelId);
	
	//根据项目id查询
	public List<SysProjecAndModel> findBySysProjecId(Integer projectId);
	
	//根据项目id查询
	public List<SysModel> findBySysProjecIdPro(Integer projectId);

	public JSONObject updateProjec(String modelIdList,String projectName
			,String userName,String password,Integer projectId,Integer userId);
	
	public JSONObject insertProjec(String projectName,Integer userId,Integer type);
	
	public JSONObject insertPro(String modelIdList,Integer projectId);
}
