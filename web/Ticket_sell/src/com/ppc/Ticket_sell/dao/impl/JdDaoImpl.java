package com.ppc.Ticket_sell.dao.impl;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.JurisdictionBean;
import com.ppc.Ticket_sell.dao.BaseDao;
import com.ppc.Ticket_sell.dao.JdDao;

public class JdDaoImpl extends BaseDaoImpl implements JdDao {

	BaseDao dao= new BaseDaoImpl();
	@Override
	public ArrayList<JurisdictionBean> sqlQuery(int pageNum, int pageSize,
			JurisdictionBean bean) {
		String hql="from JurisdictionBean where 1=1"+this.getHql(bean);
		ArrayList<JurisdictionBean> list=(ArrayList<JurisdictionBean>) dao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public Long sqlGetTotal(JurisdictionBean bean) {
		String hql="select count(*) from JurisdictionBean where 1=1"+this.getHql(bean);
		Long total=dao.getTotal(hql);
		return total;
	}

	@Override
	public Boolean sqlAddOrUpdate(JurisdictionBean bean) {
		Boolean flag=false;
		try{
			dao.savaOrUpdate(bean);
			flag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public Boolean sqlDelete(JurisdictionBean bean) {
		Boolean flag=false;
		try{
			dao.delete(bean);
			flag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}
	
	public String getHql(JurisdictionBean bean){
		String hql="";
		if(bean.getJurisdictionNum()!=null && bean.getJurisdictionNum().toString()!=""){
			hql+="and jurisdictionNum='"+bean.getJurisdictionNum().toString()+"'";
		}
		if(bean.getJurisdictionName()!=null && bean.getJurisdictionName()!=""){
			hql+="and jurisdictionName="+bean.getJurisdictionName();
		}
		return hql;
	}

}
