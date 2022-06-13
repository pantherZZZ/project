package com.th.system.po;

import java.io.Serializable;
//sys_modelAndDetectionFacility表
public class SysModelAndDetectionFacility implements Serializable {
  
	private int detectionFacilityId;
	
	private int modelId;

	public int getDetectionFacilityId() {
		return detectionFacilityId;
	}

	public void setDetectionFacilityId(int detectionFacilityId) {
		this.detectionFacilityId = detectionFacilityId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

}
