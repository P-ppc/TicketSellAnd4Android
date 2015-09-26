package com.ppc.ticket4Android.bean;

import java.io.Serializable;

public class PhoneUserBean implements Serializable
	{
		private String userName;
		private String password;
		private Long tel;
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

		public Long getTel() {
			return tel;
		}

		public void setTel(Long tel) {
			this.tel = tel;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
