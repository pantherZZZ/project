package com.th.system.po;


import java.io.Serializable;
//sys_type表
//设备类型类
public class SysType implements Serializable {

	private int typeId;//设备类型id
		
	private String typeName;//设备类型名称

	private int plantId;
	
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}	
	
}
