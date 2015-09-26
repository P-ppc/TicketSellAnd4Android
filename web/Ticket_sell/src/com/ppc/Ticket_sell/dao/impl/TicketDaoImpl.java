package com.ppc.Ticket_sell.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ppc.Ticket_sell.bean.TicketBean;
import com.ppc.Ticket_sell.dao.BaseDao;
import com.ppc.Ticket_sell.dao.TicketDao;

public  class TicketDaoImpl extends BaseDaoImpl implements TicketDao  {

	BaseDao baseDao= new BaseDaoImpl();
	
	
	@Override
	public ArrayList<TicketBean> sqlQueryAll(int pageNum,int pageSize,TicketBean bean) {
		String hql="from TicketBean Where 1=1"+this.getHql(bean);
		ArrayList<TicketBean> list=(ArrayList<TicketBean>) baseDao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public Long sqlGetTotal(TicketBean bean) {
		String hql="select count(*) from TicketBean Where 1=1"+this.getHql(bean);
		return baseDao.getTotal(hql);
	}

	@Override
	public TicketBean sqlFindById(String id) {
		String hql="from TicketBean where ticketNum="+id;
		ArrayList<TicketBean> list=(ArrayList<TicketBean>) baseDao.getQueryByPage(1, 10, hql);
		if(list.size()==1){
		 return list.get(0);
		}else
	     return null;
	}
	
	 public String getHql(TicketBean bean){
		 String hql="";
			if(bean.getTicketNum()!=null && bean.getTicketNum().toString()!="")
			{
				hql+="and ticketNum="+bean.getTicketNum().toString();
			}
			if(bean.getStart_station()!=null && bean.getStart_station()!="")
			{
				hql+="and start_station='"+bean.getStart_station().toString()+"'";
			}
			if(bean.getEnd_station()!=null && bean.getEnd_station()!="")
			{
				hql+="and end_station='"+bean.getEnd_station().toString()+"'";
			}
			if(bean.getPrice()!=null && bean.getPrice().toString()!="")
			{
				hql+="and price="+bean.getPrice().toString();
			}
			if(bean.getTime()!=null && bean.getTime().toString()!="")
			{
				hql+="and time="+bean.getTime().toString();
			}
			if(bean.getMotorcoach_number()!=null &&bean.getMotorcoach_number().toString()!=null)
			{
				hql+="and motorcoach_number="+bean.getMotorcoach_number().toString();
			}
			if(bean.getSeatNum()!=null && bean.getSeatNum().toString()!="")
			{
				hql+="and seatNum="+bean.getSeatNum().toString();
			}
		 return hql;
	 }

	@Override
	public ArrayList<TicketBean> sqlQuerySellByPage(int pageNum, int pageSize,
			TicketBean bean,String date) {
		Date today= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr=format.format(today);
		Date now= new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr=newFormat.format(now);
		String hql="from TicketBean where ('%s'='%s' and time>'%s'  or '%s'<'%s' )"+this.getHql(bean);
		hql=String.format(hql,todayStr,date,nowStr,todayStr,date);
		ArrayList<TicketBean> list=(ArrayList<TicketBean>) baseDao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public Long sqlGetSellTotal(TicketBean bean,String date) {
		Date today= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr=format.format(today);
		Date now= new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr=newFormat.format(now);
		String hql="select count(*) from TicketBean where ('%s'='%s' and time>'%s'  or '%s'<'%s' )"+this.getHql(bean);
		hql=String.format(hql,todayStr,date,nowStr,todayStr,date);
		return baseDao.getTotal(hql);
	}

	@Override
	public ArrayList<TicketBean> sqlQuerySell(
			TicketBean bean, String date) {
		Date today= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr=format.format(today);
		Date now= new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr=newFormat.format(now);
		String hql="from TicketBean where ('%s'='%s' and time>'%s'  or '%s'<'%s' )"+this.getHql(bean);
		hql=String.format(hql,todayStr,date,nowStr,todayStr,date);
		ArrayList<TicketBean> list=(ArrayList<TicketBean>) baseDao.getQueryAll(hql);
		return list;
	}


   
	


}
