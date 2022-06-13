package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysTypeMapper;
import com.th.system.po.SysType;
import com.th.system.service.SysTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service("SysTypeServiceImpl")
@Transactional
public class SysTypeServiceImpl implements SysTypeService{

	@Autowired
	private SysTypeMapper sysTypeMapper;
	
	@Override
	public Integer insertType(SysType sysType) {
		// TODO Auto-generated method stub
		return sysTypeMapper.insertType(sysType);
	}

	@Override
	public List<SysType> findTypeAll(Integer plantId) {
		// TODO Auto-generated method stub
		return sysTypeMapper.findTypeAll(plantId);
	}

}
