package com.ppc.Ticket_sell.service;

import com.ppc.Ticket_sell.bean.PhoneUserBean;

public interface PhoneUserManageService {

	public Object getPhoneUserInfo(PhoneUserBean bean);
	
	public Boolean register(PhoneUserBean bean);
	
	public Boolean PhoneUserDelete(PhoneUserBean bean);
	
	public Boolean resetPassword(PhoneUserBean bean);
	
}
