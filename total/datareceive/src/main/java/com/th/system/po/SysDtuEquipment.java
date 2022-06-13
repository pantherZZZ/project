package com.th.system.po;

import java.io.Serializable;


public class SysDtuEquipment implements Serializable{
	
	private int dtuEquipmentId;
	
	private String dtuEquipmentName;
	
	private int plantId;
	
	private String employ;//1是绑定2是未绑定
	
	public int getDtuEquipmentId() {
		return dtuEquipmentId;
	}

	public void setDtuEquipmentId(int dtuEquipmentId) {
		this.dtuEquipmentId = dtuEquipmentId;
	}

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public String getDtuEquipmentName() {
		return dtuEquipmentName;
	}

	public void setDtuEquipmentName(String dtuEquipmentName) {
		this.dtuEquipmentName = dtuEquipmentName;
	}

	public String getEmploy() {
		return employ;
	}

	public void setEmploy(String employ) {
		this.employ = employ;
	}
}
