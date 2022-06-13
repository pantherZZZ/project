package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysUnitsMapper;
import com.th.system.po.SysUnits;
import com.th.system.service.SysUnitsService;
import org.springframework.transaction.annotation.Transactional;

@Service("sysUnitsServiceImpl")
@Transactional
public class SysUnitsServiceImpl implements SysUnitsService{

	@Autowired
	private SysUnitsMapper sysUnitsMapper;

	@Override
	public Integer insertUnit(String unit, String unitName) {
		SysUnits units = new SysUnits();
//		units.setUnitsId(unitId);
		units.setUnit(unit);
		units.setUnitName(unitName);
		return sysUnitsMapper.insertUnit(units);
	}

	@Override
	public SysUnits findById(Integer unitId) {
		// TODO Auto-generated method stub 
		return sysUnitsMapper.findById(unitId); 
	}

	@Override
	public Integer updateUnits(Integer unitId, String unit, String unitName) {
		SysUnits units = new SysUnits();
		units.setUnitsId(unitId);
		units.setUnit(unit);
		units.setUnitName(unitName);
		return sysUnitsMapper.updateUnits(units);
	}

	@Override
	public List<SysUnits> findByfactorId(Integer factorId) {
		// TODO Auto-generated method stub
		return sysUnitsMapper.findByfactorId(factorId);
	}
	
	
}
