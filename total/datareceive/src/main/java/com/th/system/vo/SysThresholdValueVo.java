package com.th.system.vo;

import com.th.system.po.SysThresholdValue;

public class SysThresholdValueVo extends SysThresholdValue{

	private String factorName;
	
	private String measureName;//测点名称
	
	private int factorId;
	
	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	
}
