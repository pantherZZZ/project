package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysModelNumber;

public interface SysModelNumberMapper {

	public Integer insertSysModelNumber(SysModelNumber sysModelNumber);
	
	public List<SysModelNumber> findAll(@Param("typeId")Integer typeId);
}
