package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.ContactsBean;

public interface ContactsDao extends BaseDao{
    
	public ArrayList<ContactsBean> sqlQueryByPage(int pageNum,int pageSize,ContactsBean bean);
	
	public ArrayList<ContactsBean> sqlQuery(ContactsBean bean);
	
	public Long sqlGetTotal(ContactsBean bean);
	
	
}
