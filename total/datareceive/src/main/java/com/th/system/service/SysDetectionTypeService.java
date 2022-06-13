package com.th.system.service;

import java.util.List;

import com.th.system.po.SysDetectionType;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysDetectionTypeService {
	
	//查询全部
	public Integer insertDetection(Integer detectionTypeId,@Param("detectionTypeName")String detectionTypeName
			,@Param("isUsable")String isUsable);
	
	//查询全部
	public List<SysDetectionType> findAll();
	
}
