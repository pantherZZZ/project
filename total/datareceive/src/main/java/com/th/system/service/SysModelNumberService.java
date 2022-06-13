package com.th.system.service;

import java.util.List;

import com.th.system.po.SysModelNumber;

public interface SysModelNumberService {

    public Integer insertSysModelNumber(Integer modelNumberId,String modelNumberName);
	
	public List<SysModelNumber> findAll(Integer typeId);
	
}
