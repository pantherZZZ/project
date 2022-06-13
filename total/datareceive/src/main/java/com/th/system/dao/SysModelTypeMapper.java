package com.th.system.dao;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysModelTypeMapper {

	public Integer insertModelType(@Param("modelId")Integer modelId,@Param("detectionTypeId")Integer detectionTypeId);
	
}
