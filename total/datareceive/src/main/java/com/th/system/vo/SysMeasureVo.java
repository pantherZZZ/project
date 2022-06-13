package com.th.system.vo;

import com.th.system.po.SysMeasure;

public class SysMeasureVo extends SysMeasure {
	
	private String factorName;

	private String equipmentName;
	
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	
}
