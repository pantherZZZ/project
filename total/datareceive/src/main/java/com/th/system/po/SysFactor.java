package com.th.system.po;

import java.io.Serializable;
//sys_factor
//检测因素
public class SysFactor implements Serializable {

	private int factorId;//检测因素id
	
	private String factorName;//检测因素名称
	
	private String isPrivate;//是否是私有的 1是 2不是
	
	private int unitsId;//单位id

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public int getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(int unitsId) {
		this.unitsId = unitsId;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	
}
