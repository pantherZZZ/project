package com.th.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysModelTypeMapper;
import com.th.system.service.SysModelTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service("sysModelTypeServiceImpl")
@Transactional
public class SysModelTypeServiceImpl implements SysModelTypeService{

	@Autowired
	private SysModelTypeMapper sysModelTypeMapper;

	@Override
	public Integer insertModelType(Integer modelId, Integer detectionTypeId) {
		// TODO Auto-generated method stub
		return sysModelTypeMapper.insertModelType(modelId, detectionTypeId);
	}
	
}
