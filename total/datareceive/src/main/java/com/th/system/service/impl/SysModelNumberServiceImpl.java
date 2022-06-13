package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysModelNumberMapper;
import com.th.system.po.SysModelNumber;
import com.th.system.service.SysModelNumberService;
import org.springframework.transaction.annotation.Transactional;

@Service("SysModelNumberServiceImpl")
@Transactional
public class SysModelNumberServiceImpl implements SysModelNumberService{

	@Autowired
	private SysModelNumberMapper sysModelNumberMapper;
	
	@Override
	public Integer insertSysModelNumber(Integer modelNumberId,String modelNumberName) {
		SysModelNumber modelNumber = new SysModelNumber();
		modelNumber.setModelNumberId(modelNumberId);
		modelNumber.setModelNumberName(modelNumberName);
		return sysModelNumberMapper.insertSysModelNumber(modelNumber);
	}

	@Override
	public List<SysModelNumber> findAll(Integer typeId) {
		// TODO Auto-generated method stub
		return sysModelNumberMapper.findAll(typeId);
	}

}
