package com.th.system.dao;

import java.util.List;

import com.th.system.po.SysModel;
import com.th.system.po.SysProjecAndModel;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysProjecAndModelMapper {
	//新增关联表
	public Integer insert(SysProjecAndModel sysProjecAndModel);
	
	//根据modelId 或者 projectId删除
	public Integer delect(@Param("projectId")Integer projectId,@Param("modelId")Integer modelId);
	
	//根据项目id查询
	public List<SysProjecAndModel> findBySysProjecId(@Param("projectId")Integer projectId);

	//根据项目id查询
	public List<SysModel> findBySysProjecIdPro(@Param("projectId")Integer projectId);
}
