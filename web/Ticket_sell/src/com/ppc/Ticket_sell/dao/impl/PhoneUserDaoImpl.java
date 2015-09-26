package com.ppc.Ticket_sell.dao.impl;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.PhoneUserBean;
import com.ppc.Ticket_sell.dao.BaseDao;
import com.ppc.Ticket_sell.dao.PhoneUserDao;
import com.ppc.Ticket_sell.util.MyUtils;

public class PhoneUserDaoImpl extends BaseDaoImpl implements PhoneUserDao  {

	BaseDao dao= new BaseDaoImpl();
	@Override
	public ArrayList<PhoneUserBean> sqlQueryByPage(int pageNum, int pageSize,
			PhoneUserBean bean) {
		String hql="from PhoneUserBean where 1=1"+this.getHql(bean);
		ArrayList<PhoneUserBean> list=(ArrayList<PhoneUserBean>) dao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public Long sqlGetTotal(PhoneUserBean bean) {
		String hql="from PhoneUserBean where 1=1"+this.getHql(bean);
		Long total=dao.getTotal(hql);
		return total;
	}

	@Override
	public ArrayList<PhoneUserBean> sqlQuery(PhoneUserBean bean) {
		String hql="from PhoneUserBean where 1=1"+this.getHql(bean);
		ArrayList<PhoneUserBean> list=(ArrayList<PhoneUserBean>) dao.getQueryAll(hql);
		return list;
	}

	

	public String getHql(PhoneUserBean bean){
		String hql="";
		if(MyUtils.isNotNull(bean.getUserName())){
			hql+="and userName='"+bean.getUserName().trim()+"'";
		}
		if(MyUtils.isNotNull(bean.getPassword())){
			hql+="and password='"+bean.getPassword().trim()+"'";
		}
		if(MyUtils.isNotNull(bean.getTel())){
			hql+="and tel ='"+bean.getTel().toString()+"'";
		}
		return hql;
	}
	
}
