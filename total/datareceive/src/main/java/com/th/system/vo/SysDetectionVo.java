package com.th.system.vo;

import com.th.system.po.SysDetection;

public class SysDetectionVo extends SysDetection{

	private String detectionTypeName;
	
	private String factorName;

	public String getDetectionTypeName() {
		return detectionTypeName;
	}

	public void setDetectionTypeName(String detectionTypeName) {
		this.detectionTypeName = detectionTypeName;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	
}
