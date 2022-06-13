package com.th.system.po;

import java.io.Serializable;
//sys_thresholdValue表
//阀值类
public class SysThresholdValue implements Serializable{

	private int thresholdValueId;//阀值id
	
	private int modelId;//模型id

	private String maximum1;//一级阀值，最大值
	
	private String minimum1;//一级阀值，最小值
	
	private String maximum2;//二级阀值，最大值
	
	private String minimum2;//二级阀值，最小值
	
	private String maximum3;//三级阀值，最大值

	private String minimum3;//三级阀值，最小值
	
	private int measureId;//测点id
	
	private String show;//1显示
	
	private int equipmentId;//设备id

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}


	public String getMaximum3() {
		return maximum3;
	}

	public void setMaximum3(String maximum3) {
		this.maximum3 = maximum3;
	}

	public String getMinimum3() {
		return minimum3;
	}

	public void setMinimum3(String minimum3) {
		this.minimum3 = minimum3;
	}

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

	public String getMaximum1() {
		return maximum1;
	}

	public void setMaximum1(String maximum1) {
		this.maximum1 = maximum1;
	}

	public String getMinimum1() {
		return minimum1;
	}

	public void setMinimum1(String minimum1) {
		this.minimum1 = minimum1;
	}

	public String getMaximum2() {
		return maximum2;
	}

	public void setMaximum2(String maximum2) {
		this.maximum2 = maximum2;
	}

	public String getMinimum2() {
		return minimum2;
	}

	public void setMinimum2(String minimum2) {
		this.minimum2 = minimum2;
	}
	
}
