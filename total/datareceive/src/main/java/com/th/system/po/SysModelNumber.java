package com.th.system.po;

import java.io.Serializable;

public class SysModelNumber implements Serializable  {

	private int modelNumberId;//型号id
	
	private String modelNumberName;//型号名称
	
	private int typeId;
	
	
	public int getModelNumberId() {
		return modelNumberId;
	}

	public void setModelNumberId(int modelNumberId) {
		this.modelNumberId = modelNumberId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getModelNumberName() {
		return modelNumberName;
	}

	public void setModelNumberName(String modelNumberName) {
		this.modelNumberName = modelNumberName;
	}
}
