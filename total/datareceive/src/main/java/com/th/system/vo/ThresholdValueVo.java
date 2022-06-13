package com.th.system.vo;

public class ThresholdValueVo {

	private int thresholdValueId;
	
	private int modelId;
	
	private String thresholdValue1;
	
	private String thresholdValue2;
	
	private String thresholdValue3;
	
	private int measureId;
	
	private String measureName;

	private int factorId;
	
	private String factorName;
	
	public int getThresholdValueId() {
		return thresholdValueId;
	}

	public void setThresholdValueId(int thresholdValueId) {
		this.thresholdValueId = thresholdValueId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getMeasureId() {
		return measureId;
	}

	public void setMeasureId(int measureId) {
		this.measureId = measureId;
	}

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getThresholdValue1() {
		return thresholdValue1;
	}

	public void setThresholdValue1(String thresholdValue1) {
		this.thresholdValue1 = thresholdValue1;
	}

	public String getThresholdValue2() {
		return thresholdValue2;
	}

	public void setThresholdValue2(String thresholdValue2) {
		this.thresholdValue2 = thresholdValue2;
	}

	public String getThresholdValue3() {
		return thresholdValue3;
	}

	public void setThresholdValue3(String thresholdValue3) {
		this.thresholdValue3 = thresholdValue3;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	
}
