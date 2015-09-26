package com.ppc.Ticket_sell.bean;

public class PhoneUserBean {

	private String userName;
	private String password;
	private Integer tel;
	private String email;
	
	public PhoneUserBean(){}

	@Override
	public String toString() {
		return "PhoneUserBean [userName=" + userName + ", password=" + password
				+ ", tel=" + tel + ", email=" + email + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
