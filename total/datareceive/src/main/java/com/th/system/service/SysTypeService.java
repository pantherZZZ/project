package com.th.system.service;

import java.util.List;

import com.th.system.po.SysType;

public interface SysTypeService {
	
		//新增
		public Integer insertType(SysType sysType);
		
		//查询所有
		public List<SysType> findTypeAll(Integer plantId);
}
