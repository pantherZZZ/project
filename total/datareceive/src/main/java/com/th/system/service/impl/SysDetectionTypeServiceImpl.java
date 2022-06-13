package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysDetectionTypeMapper;
import com.th.system.po.SysDetectionType;
import com.th.system.service.SysDetectionTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service("sysDetectionTypeServiceImpl")
@Transactional
public class SysDetectionTypeServiceImpl implements SysDetectionTypeService{

	@Autowired
	private SysDetectionTypeMapper sysDetectionTypeMapper;
	
	@Override
	public Integer insertDetection(Integer detectionTypeId, String detectionTypeName, String isUsable) {
		// TODO Auto-generated method stub
		return sysDetectionTypeMapper.insertDetection(detectionTypeId, detectionTypeName, isUsable);
	}

	@Override
	public List<SysDetectionType> findAll() {
		// TODO Auto-generated method stub
		return sysDetectionTypeMapper.findAll();
	}

}
