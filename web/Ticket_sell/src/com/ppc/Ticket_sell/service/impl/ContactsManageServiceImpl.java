package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.ContactsBean;
import com.ppc.Ticket_sell.dao.ContactsDao;
import com.ppc.Ticket_sell.dao.impl.ContactsDaoImpl;
import com.ppc.Ticket_sell.service.ContactsManageService;

public class ContactsManageServiceImpl implements ContactsManageService {
    
	ContactsDao dao = new ContactsDaoImpl();
	
	@Override
	public Object getContactsInfo(ContactsBean bean) {
		ArrayList<ContactsBean> list= dao.sqlQuery(bean);
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("rows", list);
		JSONObject object = JSONObject.fromObject(map);
		return object;
	}

	@Override
	public Boolean contactsAddOrUpdate(ContactsBean bean) {
		try{
			dao.savaOrUpdate(bean);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean contactsDelete(ContactsBean bean) {
		try{
			dao.delete(bean);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
