package com.th.system.po;

import java.io.Serializable;
import java.util.List;

//sys_model表
//模型类
public class SysModel implements Serializable {

	private int modelId;//模型id
	
	private String modelName;//模型名称
	
	private String coordinateX;//坐标X轴
	
	private String coordinateY;//坐标Y轴
	
	private String purpose;//目的
	
	private String gist;//依据
	
	private String sketchMap;//示意图
	
	private String describe;//描述
	
	private String isBinding;//是否被绑定 2绑定 1未绑定
	
	private int userId;//创建人id

	private int detectionTypeId;
	
	private String address;

	private String  longitude;   //经度

	private String latitude;   //纬度

	private String lnglat;


	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	//	private String lnglat;
//
//	public String getLnglat() {
//		return lnglat;
//	}
//
//	public void setLnglat(String lnglat) {
//		this.lnglat = lnglat;
//	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(String isBinding) {
		this.isBinding = isBinding;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDetectionTypeId() {
		return detectionTypeId;
	}

	public void setDetectionTypeId(int detectionTypeId) {
		this.detectionTypeId = detectionTypeId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}

	public String getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getGist() {
		return gist;
	}

	public void setGist(String gist) {
		this.gist = gist;
	}

	public String getSketchMap() {
		return sketchMap;
	}

	public void setSketchMap(String sketchMap) {
		this.sketchMap = sketchMap;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
