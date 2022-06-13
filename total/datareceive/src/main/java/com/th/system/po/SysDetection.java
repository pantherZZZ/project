package com.th.system.po;

import java.io.Serializable;

public class SysDetection implements Serializable{

	private int detectionId;//检测模型id
	
	private String detectionName;//检测模型名称
	
	private int factorId;//检测因素id
	
	private int detectionTypeId;//检测模型id
	
	private int userId;//用户id
	
	public int getDetectionId() {
		return detectionId;
	}

	public void setDetectionId(int detectionId) {
		this.detectionId = detectionId;
	}

	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public int getDetectionTypeId() {
		return detectionTypeId;
	}

	public void setDetectionTypeId(int detectionTypeId) {
		this.detectionTypeId = detectionTypeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDetectionName() {
		return detectionName;
	}

	public void setDetectionName(String detectionName) {
		this.detectionName = detectionName;
	}

	
}
