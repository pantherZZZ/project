package com.th.system.service;

import java.util.List;

import com.th.system.dao.SysPlantMapper;
import com.th.system.po.SysPlant;

public interface SysPlantService {
	
	//新增
	public Integer insertPlant(SysPlant sysPlant);
	
	//查询所有
	public List<SysPlantMapper> findAll();
}
