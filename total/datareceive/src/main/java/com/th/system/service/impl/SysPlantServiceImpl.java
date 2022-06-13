package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysPlantMapper;
import com.th.system.po.SysPlant;
import com.th.system.service.SysPlantService;
import org.springframework.transaction.annotation.Transactional;

@Service("SysPlantServiceImpl")
@Transactional
public class SysPlantServiceImpl implements SysPlantService{

	@Autowired
	private SysPlantMapper sysPlantMapper;
	
	@Override
	public Integer insertPlant(SysPlant sysPlant) {
		// TODO Auto-generated method stub
		return sysPlantMapper.insertPlant(sysPlant);
	}

	@Override
	public List<SysPlantMapper> findAll() {
		// TODO Auto-generated method stub
		return sysPlantMapper.findAll();
	}

}
