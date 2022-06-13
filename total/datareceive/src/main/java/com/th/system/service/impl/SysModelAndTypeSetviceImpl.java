package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysModelAndTypeMapper;
import com.th.system.po.SysModelAndType;
import com.th.system.po.SysType;
import com.th.system.service.SysModelAndTypeService;
import org.springframework.transaction.annotation.Transactional;

@Service("SysModelAndTypeSetviceImpl")
@Transactional
public class SysModelAndTypeSetviceImpl implements SysModelAndTypeService{

	@Autowired
	private SysModelAndTypeMapper sysModelAndTypeMapper;

	@Override
	public Integer insert(SysModelAndType sysModelAndType) {
		// TODO Auto-generated method stub
		return sysModelAndTypeMapper.insert(sysModelAndType);
	}

	@Override
	public List<SysType> fineByModleId(Integer modelId) {
		// TODO Auto-generated method stub
		return sysModelAndTypeMapper.fineByModleId(modelId);
	}
	
}
