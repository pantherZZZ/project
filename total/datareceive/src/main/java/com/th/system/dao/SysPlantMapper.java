package com.th.system.dao;

import java.util.List;

import com.th.system.po.SysPlant;

//sys_plant表
//工厂类
public interface SysPlantMapper {

	//新增
	public Integer insertPlant(SysPlant sysPlant);
	
	//查询所有
	public List<SysPlantMapper> findAll();
	
}
