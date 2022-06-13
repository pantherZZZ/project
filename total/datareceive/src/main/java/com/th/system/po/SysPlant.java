package com.th.system.po;

import java.io.Serializable;

//sys_plant表
//工厂类
public class SysPlant implements Serializable {

	private int plantId;//工厂id
	
	private String plantName;//工厂名称

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	
}
