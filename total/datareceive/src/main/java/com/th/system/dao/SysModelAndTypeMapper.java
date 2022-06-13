package com.th.system.dao;

import java.util.List;

import com.th.system.po.SysModelAndType;
import com.th.system.po.SysType;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysModelAndTypeMapper {

	//新增
	public Integer insert(SysModelAndType sysModelAndType);
	
	//查询项目选择的设备类型
	public List<SysType> fineByModleId(@Param("modelId")Integer modelId);
}
