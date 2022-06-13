package com.th.system.service;

import java.util.List;

import com.th.system.po.SysDtuEquipment;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysDtuEquipmentService {

	public List<SysDtuEquipment> findByPlantId(Integer plantId);
	
	public Integer updateDtuEquipment(SysDtuEquipment sysDtuEquipment);
	
}
