package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysUnits;

public interface SysUnitsMapper {
	
	//新增
	public Integer insertUnit(SysUnits sysUnits);
	
	//id查询
	public SysUnits findById(@Param("unitId")Integer unitId);
	
	//id查询
	public List<SysUnits> findByfactorId(@Param("factorId")Integer factorId);
	
	//修改
	public Integer updateUnits(SysUnits sysUnits);
}
