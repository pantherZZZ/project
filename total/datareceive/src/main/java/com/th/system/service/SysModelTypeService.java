package com.th.system.service;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysModelTypeService {
	public Integer insertModelType(Integer modelId,Integer detectionTypeId);
	
}
