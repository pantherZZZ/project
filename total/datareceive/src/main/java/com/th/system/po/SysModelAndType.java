package com.th.system.po;

import java.io.Serializable;
//sys_modelAndType表
public class SysModelAndType implements Serializable  {

	private int typeId;
	
	private int modelId;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	
}
