package com.th.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.system.dao.SysDtuEquipmentMapper;
import com.th.system.po.SysDtuEquipment;
import com.th.system.service.SysDtuEquipmentService;
import org.springframework.transaction.annotation.Transactional;

@Service("SysDtuEquipmentServiceImpl")
@Transactional
public class SysDtuEquipmentServiceImpl implements SysDtuEquipmentService{

	@Autowired
	private SysDtuEquipmentMapper sysDtuEquipmentMapper;

	@Override
	public List<SysDtuEquipment> findByPlantId(Integer plantId) {
		// TODO Auto-generated method stub
		return sysDtuEquipmentMapper.findByPlantId(plantId);
	}

	@Override
	public Integer updateDtuEquipment(SysDtuEquipment sysDtuEquipment) {
		// TODO Auto-generated method stub
		return sysDtuEquipmentMapper.updateDtuEquipment(sysDtuEquipment);
	}
	
}
