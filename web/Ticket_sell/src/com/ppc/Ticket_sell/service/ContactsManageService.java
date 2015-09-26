package com.ppc.Ticket_sell.service;

import com.ppc.Ticket_sell.bean.ContactsBean;

public interface ContactsManageService {
    
	public Object getContactsInfo(ContactsBean bean);
	
	public Boolean contactsAddOrUpdate(ContactsBean bean);
	
	public Boolean contactsDelete(ContactsBean bean);
}
