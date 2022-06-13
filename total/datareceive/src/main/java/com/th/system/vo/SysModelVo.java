package com.th.system.vo;

import com.th.system.po.SysModel;

import java.util.List;

public class SysModelVo extends SysModel{
	
	private String pictureName;
	
	private String picturePath;

	private String detectionTypeName;
	
	private String projectName;//项目名

	private List<String> alnglat;


	public List<String> getAlnglat() {
		return alnglat;
	}

	public void setAlnglat(List<String> alnglat) {
		this.alnglat = alnglat;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDetectionTypeName() {
		return detectionTypeName;
	}

	public void setDetectionTypeName(String detectionTypeName) {
		this.detectionTypeName = detectionTypeName;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
}
