package com.th.system.vo;

import com.th.system.po.SysDtu;

public class SysDtuVo extends SysDtu{

	private String equipmentName;//设备名称
	
	private String plantName;//工厂名称
	
	private String modelNumberName;//型号名称

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getModelNumberName() {
		return modelNumberName;
	}

	public void setModelNumberName(String modelNumberName) {
		this.modelNumberName = modelNumberName;
	}
}
