package com.th.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysModelAndDetectionFacilityMapper;
import com.th.system.po.SysModelAndDetectionFacility;
import com.th.system.service.SysModelAndDetectionFacilityService;
import org.springframework.transaction.annotation.Transactional;

@Service("SysModelAndDetectionFacilityServiceImpl")
@Transactional
public class SysModelAndDetectionFacilityServiceImpl implements SysModelAndDetectionFacilityService{
	
	@Autowired
	private SysModelAndDetectionFacilityMapper sysModelAndDetectionFacilityMapper;
	
	@Override
	public Integer insert(SysModelAndDetectionFacility sysModelAndDetectionFacility) {
		// TODO Auto-generated method stub
		return sysModelAndDetectionFacilityMapper.insert(sysModelAndDetectionFacility);
	}

}
