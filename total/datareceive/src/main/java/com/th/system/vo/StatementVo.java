package com.th.system.vo;

public class StatementVo {

	private int equipmentId;
	
	private String statementMessage;
	
	private String district;
	
	private String statementTime;

	private String specification;//型号 
	
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getStatementMessage() {
		return statementMessage;
	}

	public void setStatementMessage(String statementMessage) {
		this.statementMessage = statementMessage;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStatementTime() {
		return statementTime;
	}

	public void setStatementTime(String statementTime) {
		this.statementTime = statementTime;
	}
}
