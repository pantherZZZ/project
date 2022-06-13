package com.th.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.th.system.po.SysUnits;

public interface SysUnitsService {
		
	    //新增
		public Integer insertUnit(String unit,String unitName);
		
		//id查询
		public SysUnits findById(Integer unitId);
		
		//修改
		public Integer updateUnits(Integer unitId,String unit,String unitName);
	
		//id查询
		public List<SysUnits> findByfactorId(Integer factorId);
		
}
