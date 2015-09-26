package com.ppc.ticket4Android.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.ppc.ticket4Android.bean.ContactsBean;

public interface ContactsService {
    
	//查询联系人-ArrayList<ContactsBean>
	public ArrayList<ContactsBean> getContacts(ContactsBean bean);
	
	//查询联系人-ArrayList<HashMap<String,Object>>
	
	public ArrayList<HashMap<String,Object>> getContactsListItem(ContactsBean bean);
	
	public Boolean ContactsAddOrUpdate(ContactsBean bean);
	
	public Boolean ContactsDelete(ContactsBean bean);
}
