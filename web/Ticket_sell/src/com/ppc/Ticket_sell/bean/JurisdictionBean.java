package com.ppc.Ticket_sell.bean;

public class JurisdictionBean {

	private Integer jurisdictionNum;
	private String jurisdictionName;
	
	public JurisdictionBean(){}

	@Override
	public String toString() {
		return "JurisdictionBean [jurisdictionNum=" + jurisdictionNum
				+ ", jurisdictionName=" + jurisdictionName + "]";
	}

	public Integer getJurisdictionNum() {
		return jurisdictionNum;
	}

	public void setJurisdictionNum(Integer jurisdictionNum) {
		this.jurisdictionNum = jurisdictionNum;
	}

	public String getJurisdictionName() {
		return jurisdictionName;
	}

	public void setJurisdictionName(String jurisdictionName) {
		this.jurisdictionName = jurisdictionName;
	}
	
	
}
