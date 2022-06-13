package com.th.system.po;

import java.io.Serializable;

//sys_detectionType
//检测类型
public class SysDetectionType implements Serializable {
		
	private int detectionTypeId;//检测类型id
	
	private String detectionTypeName;//检测类型名称
	
	private String isUsable;//是否可以用

	public int getDetectionTypeId() {
		return detectionTypeId;
	}

	public void setDetectionTypeId(int detectionTypeId) {
		this.detectionTypeId = detectionTypeId;
	}

	public String getDetectionTypeName() {
		return detectionTypeName;
	}

	public void setDetectionTypeName(String detectionTypeName) {
		this.detectionTypeName = detectionTypeName;
	}

	public String getIsUsable() {
		return isUsable;
	}

	public void setIsUsable(String isUsable) {
		this.isUsable = isUsable;
	}
	
}
