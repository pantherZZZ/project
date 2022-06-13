package com.th.system.dao;

import java.util.List;

import com.th.system.po.SysDetectionType;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysDetectionTypeMapper {
	
	//新增
	public Integer insertDetection(@Param("detectionTypeId")Integer detectionTypeId,@Param("detectionTypeName")String detectionTypeName
			,@Param("isUsable")String isUsable);
	
	//查询全部
	public List<SysDetectionType> findAll();
	
}
