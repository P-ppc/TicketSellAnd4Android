package com.ppc.Ticket_sell.dao.impl;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.ContactsBean;
import com.ppc.Ticket_sell.dao.BaseDao;
import com.ppc.Ticket_sell.dao.ContactsDao;
import com.ppc.Ticket_sell.util.MyUtils;

public class ContactsDaoImpl extends BaseDaoImpl implements ContactsDao {
    
	BaseDao dao = new BaseDaoImpl();
	@Override
	public ArrayList<ContactsBean> sqlQueryByPage(int pageNum, int pageSize,
			ContactsBean bean) {
		String hql="form ContactsBean where 1=1"+this.getHql(bean);
		ArrayList<ContactsBean> list= (ArrayList<ContactsBean>) dao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public ArrayList<ContactsBean> sqlQuery(ContactsBean bean) {
		String hql="from ContactsBean where 1=1"+this.getHql(bean);
		ArrayList<ContactsBean> list= (ArrayList<ContactsBean>) dao.getQueryAll(hql);
		return list;
	}

	@Override
	public Long sqlGetTotal(ContactsBean bean) {
		String hql="from ContactsBean where 1=1"+this.getHql(bean);
		Long total=dao.getTotal(hql);
		return total;
	}
    
	public String getHql(ContactsBean bean){
		String hql="";
		if(MyUtils.isNotNull(bean.getContactName())){
			hql+="and contactName='"+bean.getContactName()+"'";
		}
		if(MyUtils.isNotNull(bean.getContactId())){
			hql+="and contactId='"+bean.getContactId()+"'";
		}
		if(MyUtils.isNotNull(bean.getUser().getUserName())){
			hql+="and user.userName='"+bean.getUser().getUserName()+"'";
		}
		if(MyUtils.isNotNull(bean.getUser().getPassword())){
			hql+="and user.password='"+bean.getUser().getPassword();
		}
		return hql;
	}
	
	
}
