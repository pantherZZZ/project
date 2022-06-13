package com.th.system.po;

import java.io.Serializable;

public class SysModelType implements Serializable {

	private int modelId;
	
	private int detectionTypeId;

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getDetectionTypeId() {
		return detectionTypeId;
	}

	public void setDetectionTypeId(int detectionTypeId) {
		this.detectionTypeId = detectionTypeId;
	}
	
}
