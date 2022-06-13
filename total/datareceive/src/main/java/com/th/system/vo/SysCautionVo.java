package com.th.system.vo;

public class SysCautionVo {

	private String phoneNumber;//电话号码
	
	private String mailbox;//邮箱
	
	private String isUserMailbox;//邮箱通知 1通知 2不通知
	
	private String isUserNote;//短信通知 1通知 2不通知
	
	private String noDisturbing;//免打扰 2不免打扰 1免打扰
	
	private String userForbidden;//禁用 2不禁用 1禁用
	
	private String isMailbox;//邮箱通知 1通知 2不通知
	
	private String isNote;//短信通知 1通知 2不通知
	
	private String cautionGrade;//告警级别 1 2 3
	
	private String forbidden;//禁用 1禁用 2不禁用

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getIsUserMailbox() {
		return isUserMailbox;
	}

	public void setIsUserMailbox(String isUserMailbox) {
		this.isUserMailbox = isUserMailbox;
	}

	public String getIsUserNote() {
		return isUserNote;
	}

	public void setIsUserNote(String isUserNote) {
		this.isUserNote = isUserNote;
	}

	public String getNoDisturbing() {
		return noDisturbing;
	}

	public void setNoDisturbing(String noDisturbing) {
		this.noDisturbing = noDisturbing;
	}

	public String getUserForbidden() {
		return userForbidden;
	}

	public void setUserForbidden(String userForbidden) {
		this.userForbidden = userForbidden;
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

	public String getCautionGrade() {
		return cautionGrade;
	}

	public void setCautionGrade(String cautionGrade) {
		this.cautionGrade = cautionGrade;
	}

	public String getForbidden() {
		return forbidden;
	}

	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
	
}
