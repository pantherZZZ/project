package com.th.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysType;

//sys_type表
//设备类型类
public interface SysTypeMapper {
	//新增
	public Integer insertType(SysType sysType);
	//查询所有
	public List<SysType> findTypeAll(@Param("plantId")Integer plantId);
}
