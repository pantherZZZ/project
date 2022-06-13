package com.th.system.po;

import java.io.Serializable;

public class SysUnits implements Serializable{

	private int unitsId;//单位id
	
	private String unit;//单位
	
	private String unitName;//单位名称
	
	private int factorId;

	public int getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(int unitsId) {
		this.unitsId = unitsId;
	}

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
