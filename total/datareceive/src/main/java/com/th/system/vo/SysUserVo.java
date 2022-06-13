package com.th.system.vo;

import com.th.system.po.SysUnits;
import com.th.system.po.SysUser;

public class SysUserVo extends SysUser {

	private String projectName;//项目名

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
