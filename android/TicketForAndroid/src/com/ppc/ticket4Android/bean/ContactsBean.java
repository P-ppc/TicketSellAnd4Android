package com.ppc.ticket4Android.bean;

import java.io.Serializable;

public class ContactsBean implements Serializable
	{
		private Integer contactNo;
		private String  contactName;
		private String  contactId;
		private PhoneUserBean user;
		
		public ContactsBean(){}

		@Override
		public String toString() {
			return "ContactsBean [contactNo=" + contactNo + ", contactName="
					+ contactName + ", contactId=" + contactId + ", user=" + user
					+ "]";
		}

		public Integer getContactNo() {
			return contactNo;
		}

		public void setContactNo(Integer contactNo) {
			this.contactNo = contactNo;
		}

		public String getContactName() {
			return contactName;
		}

		public void setContactName(String contactName) {
			this.contactName = contactName;
		}

		public String getContactId() {
			return contactId;
		}

		public void setContactId(String contactId) {
			this.contactId = contactId;
		}

		public PhoneUserBean getUser() {
			return user;
		}

		public void setUser(PhoneUserBean user) {
			this.user = user;
		}
	}
