package com.th.system.po;

import java.io.Serializable;

public class SysFocussing implements Serializable {
	
	private int focussingId;//id
	
	private int factorId;//因素id
	
	private String focussingType;//类型
	
	private String focussingMode;//方式
	
	private String state;//状态
	
	private String daysStartTime;//天  开始时间
	
	private String daysEndTime;//天  结束时间
	
	private String weekStartTime;//周  开始时间
	
	private String weekEndTime;//周  结束时间
	
	private String monthStartTime;//月  开始时间
	
	private String monthEndTime;//月  结束时间
	
	private int userId;
	
	private String weekStart;
	
	private String weekEnd;
	
	public String getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(String weekStart) {
		this.weekStart = weekStart;
	}

	public String getWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(String weekEnd) {
		this.weekEnd = weekEnd;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getFocussingId() {
		return focussingId;
	}

	public void setFocussingId(int focussingId) {
		this.focussingId = focussingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getFactorId() {
		return factorId;
	}

	public void setFactorId(int factorId) {
		this.factorId = factorId;
	}

	public String getFocussingType() {
		return focussingType;
	}

	public void setFocussingType(String focussingType) {
		this.focussingType = focussingType;
	}

	public String getFocussingMode() {
		return focussingMode;
	}

	public void setFocussingMode(String focussingMode) {
		this.focussingMode = focussingMode;
	}

	public String getDaysStartTime() {
		return daysStartTime;
	}

	public void setDaysStartTime(String daysStartTime) {
		this.daysStartTime = daysStartTime;
	}

	public String getDaysEndTime() {
		return daysEndTime;
	}

	public void setDaysEndTime(String daysEndTime) {
		this.daysEndTime = daysEndTime;
	}

	public String getWeekStartTime() {
		return weekStartTime;
	}

	public void setWeekStartTime(String weekStartTime) {
		this.weekStartTime = weekStartTime;
	}

	public String getWeekEndTime() {
		return weekEndTime;
	}

	public void setWeekEndTime(String weekEndTime) {
		this.weekEndTime = weekEndTime;
	}

	public String getMonthStartTime() {
		return monthStartTime;
	}

	public void setMonthStartTime(String monthStartTime) {
		this.monthStartTime = monthStartTime;
	}

	public String getMonthEndTime() {
		return monthEndTime;
	}

	public void setMonthEndTime(String monthEndTime) {
		this.monthEndTime = monthEndTime;
	}
	
}
