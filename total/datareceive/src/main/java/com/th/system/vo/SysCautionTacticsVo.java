package com.th.system.vo;

import com.th.system.po.SysCaution;
import com.th.system.po.SysCautionTactics;

public class SysCautionTacticsVo extends SysCautionTactics{

	private String userName;
	
	private String modelName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
}
