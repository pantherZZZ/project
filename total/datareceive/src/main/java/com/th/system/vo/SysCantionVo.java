package com.th.system.vo;

import com.th.system.po.SysCaution;

public class SysCantionVo extends SysCaution{

	private String modelName;
	
	private String measureName;
	
	private String projectName;

	private String number;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	@Override
	public String toString() {
		return "SysCantionVo [modelName=" + modelName + ", measureName=" + measureName + ", projectName=" + projectName
				+ ", number=" + number + "]";
	}
	
	
	
}
