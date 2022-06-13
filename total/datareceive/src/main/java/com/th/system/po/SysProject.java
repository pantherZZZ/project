package com.th.system.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
//sys_project表
//项目类
@TableName("sys_project")
public class SysProject implements Serializable {

	@TableId("projectId")
	private int projectId;//项目id

	@TableField("projectName")
	private String projectName;//项目名

	@TableField("userId")
	private int userId;//管理员

	@TableField("time")
	private String time;//更新时间

	@TableField("show")
	private String show;//2显示 1不显示

	@TableField("creationTime")
	private String creationTime;//创建时间

	@TableField("type")
	private int type;

	@TableField(exist = false)
	private String userName;



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
