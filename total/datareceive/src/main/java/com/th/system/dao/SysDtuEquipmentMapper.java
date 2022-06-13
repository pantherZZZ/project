package com.th.system.dao;

import java.util.List;

import com.th.system.po.SysDtuEquipment;

import io.lettuce.core.dynamic.annotation.Param;

public interface SysDtuEquipmentMapper {

	public List<SysDtuEquipment> findByPlantId(@Param("plantId")Integer plantId);
	
	public Integer updateDtuEquipment(SysDtuEquipment sysDtuEquipment);
}
