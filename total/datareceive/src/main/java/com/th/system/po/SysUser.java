package com.th.system.po;

import java.io.Serializable;

//sys_user表
//用户类
public class SysUser implements Serializable {

	private int userId;//用户id
	
	private String userName;//用户名
	
	private String loginName;//登录名
	
	private String password;//密码
	
	private String role;//权限角色 1系统管理员 2项目管理员
	
	private String phoneNumber;//电话号码
	
	private String mailbox;//邮箱
	
	private String isMailbox;//邮箱通知 1通知 2不通知
	
	private String isNote;//短信通知 1通知 2不通知
	
	private String noDisturbing;//免打扰 2不免打扰 1免打扰
	
	private String forbidden;//禁用 2不禁用 1禁用
	
	private String profilePicture;//头像地址
	
	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getForbidden() {
		return forbidden;
	}

	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getIsMailbox() {
		return isMailbox;
	}

	public void setIsMailbox(String isMailbox) {
		this.isMailbox = isMailbox;
	}

	public String getIsNote() {
		return isNote;
	}

	public void setIsNote(String isNote) {
		this.isNote = isNote;
	}

	public String getNoDisturbing() {
		return noDisturbing;
	}

	public void setNoDisturbing(String noDisturbing) {
		this.noDisturbing = noDisturbing;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", userName=" + userName + ", loginName=" + loginName + ", password="
				+ password + ", role=" + role + ", phoneNumber=" + phoneNumber + ", mailbox=" + mailbox + ", isMailbox="
				+ isMailbox + ", isNote=" + isNote + ", noDisturbing=" + noDisturbing + ", forbidden=" + forbidden
				+ ", profilePicture=" + profilePicture + "]";
	}
	
	
}
