package com.ppc.Ticket_sell.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.ppc.Ticket_sell.bean.OrderBean;
import com.ppc.Ticket_sell.dao.BaseDao;
import com.ppc.Ticket_sell.dao.OrderDao;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

	BaseDao dao= new BaseDaoImpl();
	public String getHql(OrderBean bean){
		String hql="";
		if(bean.getOrderNum()!=null && bean.getOrderNum().toString()!=""){
			hql+="and orderNum='"+bean.getOrderNum().toString()+"'";
		}
		if(bean.getOrderTime()!=null && bean.getOrderTime().toString()!=""){
			hql+="and orderTime='"+bean.getOrderTime().toString()+"'";
		}
		if(bean.getPassengerName()!=null && bean.getPassengerName()!=""){
			hql+="and passengerName='"+bean.getPassengerName()+"'";
		}
		if(bean.getPassengerId()!=null &&bean.getPassengerId()!=""){
			hql+="and passengerId='"+bean.getPassengerId()+"'";
		}
		if(bean.getSeatNo()!=null && bean.getSeatNo().toString()!=""){
			hql+="and seatNo='"+bean.getSeatNo().toString()+"'";
		}
		if(bean.getDate()!=null && bean.getDate().toString()!=""){
			hql+="and date='"+bean.getDate().toString()+"'";
		}
		if(bean.getTicket()!=null && bean.getTicket().getTicketNum()!=null && bean.getTicket().getTicketNum().toString()!=""){
			hql+="and ticket.ticketNum='"+bean.getTicket().getTicketNum().toString()+"'";
		}
		if(bean.getUser()!=null && bean.getUser().getUserName()!=null && bean.getUser().getUserName().toString()!=""){
			hql+="and user.userName='"+bean.getUser().getUserName()+"'";
		}
		if(bean.getState()!=null && bean.getState().getStateNum()!=null && bean.getState().getStateNum().toString()!=""){
			hql+="and state.stateNum='"+bean.getState().getStateNum().toString()+"'";
		}
		if(bean.getWorker()!=null && bean.getWorker().getWorkerName()!=null && bean.getWorker().getWorkerName()!=""){
			hql+="and worker.workerName='"+bean.getWorker().getWorkerName()+"'";
		}
		return hql;
	}
	@Override
	public ArrayList<OrderBean> sqlQueryByPage(int pageNum, int pageSize,
			OrderBean bean) {
		Date today= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr=format.format(today);
		Date now= new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr=newFormat.format(now);
		String hql="from OrderBean where 1=1 and (date>'"+todayStr+"' or (date='"+todayStr+"' and ticket.time>'"+nowStr+"'))"+this.getHql(bean);
		ArrayList<OrderBean> list=(ArrayList<OrderBean>) dao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public Long sqlGetTotal(OrderBean bean) {
	    String hql="select count(*) from OrderBean where 1=1"+this.getHql(bean);
	    Long total=dao.getTotal(hql);
		return total;
	}
	//获取生效的Order，即满足时间和车票编号以及state=1,2,3或者 (state=0且 orderTime 与现在时间差小于 1小时)且日期大于今天或（日期等于今天且时间大于现在）
	@Override
	public ArrayList<OrderBean> sqlQueryEffective(OrderBean bean) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderTime=format.format(calendar.getTime());
		String hql="from OrderBean where 1=1 and (state.stateNum='1' or state.stateNum='2' or state.stateNum='3' or(state.stateNum='0' and ordertime>'"+orderTime+"'))"+this.getHql(bean);
		ArrayList<OrderBean> list= (ArrayList<OrderBean>)dao.getQueryAll(hql);
		return list;
	}
	@Override
	public ArrayList<OrderBean> sqlQuery(OrderBean bean) {
		String hql="from OrderBean where 1=1"+this.getHql(bean);
		ArrayList<OrderBean> list=(ArrayList<OrderBean>) dao.getQueryAll(hql);
		return list;
	}
	@Override
	public Boolean sqlUpdate(OrderBean bean) {
		String sql="update tbOrder set stateNum='"+bean.getState().getStateNum().toString()+"'";
		if(bean.getWorker()!=null && bean.getWorker().getWorkerName()!=null){
			sql+=" , workerName='"+bean.getWorker().getWorkerName()+"'";
		}
		sql+=" where orderNum='"+bean.getOrderNum().toString()+"'";
		return dao.updateBySql(sql);
	}
	@Override
	public ArrayList<OrderBean> sqlQueryPhoneUserEffectiveUnpaidOrder(
			OrderBean bean) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderTime=format.format(calendar.getTime());
		OrderBean newBean=new OrderBean();
		newBean.setUser(bean.getUser());
		String hql="from OrderBean where 1=1 and state.stateNum='0' and orderTime >'"+orderTime+"'"+this.getHql(newBean);
		ArrayList<OrderBean> list=(ArrayList<OrderBean>) dao.getQueryAll(hql);
		return list;
	}
	@Override
	public ArrayList<OrderBean> sqlTicketRetrunQueryByPage(int pageNum,
			int pageSize, OrderBean bean) {
		Date today= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr=format.format(today);
		Date now= new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr=newFormat.format(now);
		String hql="from OrderBean where 1=1 and (state.stateNum='3' or state.stateNum='2') and (date>'"+todayStr+"' or (date='"+todayStr+"' and ticket.time>'"+nowStr+"'))"+this.getHql(bean);
		ArrayList<OrderBean> list=(ArrayList<OrderBean>) dao.getQueryByPage(pageNum, pageSize, hql);
		return list;
		
	}
	@Override
	public Long sqlTicketReturnQueryTotal(OrderBean bean) {
		Date today= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr=format.format(today);
		Date now= new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr=newFormat.format(now);
		String hql="select count(*) from OrderBean where 1=1 and (state.stateNum='3' or state.stateNum='2') and (date>'"+todayStr+"' or (date='"+todayStr+"' and ticket.time>'"+nowStr+"'))"+this.getHql(bean);
	    Long total=dao.getTotal(hql);
		return total;
	}
	@Override
	public Long sqlGetTicketGetTotal(OrderBean bean) {
		Date today= new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayStr=format.format(today);
		Date now= new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr=newFormat.format(now);
		String hql="select count(*) from OrderBean where 1=1 and (date>'"+todayStr+"' or (date='"+todayStr+"' and ticket.time>'"+nowStr+"'))"+this.getHql(bean);
		return dao.getTotal(hql);
	}

}
