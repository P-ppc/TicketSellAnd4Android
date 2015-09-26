package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.OrderBean;
import com.ppc.Ticket_sell.bean.OrderStateBean;
import com.ppc.Ticket_sell.bean.WorkerBean;
import com.ppc.Ticket_sell.dao.OrderDao;
import com.ppc.Ticket_sell.dao.impl.OrderDaoImpl;
import com.ppc.Ticket_sell.service.TicketGetService;

public class TicketGetManageServiceImpl implements TicketGetService {
	OrderDao dao= new OrderDaoImpl();
	@Override
	public Object returnOrderInfo(int pageNum, int pageSize, OrderBean bean) {
		
		ArrayList<OrderBean> list=dao.sqlTicketRetrunQueryByPage(pageNum, pageSize, bean);
	    Long total=dao.sqlTicketReturnQueryTotal(bean);
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);
		net.sf.json.JSONObject array= JSONObject.fromObject(map);
		return array;
	}
	@Override
	public Boolean OrderDelete(OrderBean bean) {
		Boolean flag=false;
		try{
			dao.delete(bean);
			flag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}
	@Override
	public Object ticketGet(OrderBean bean) {
     	OrderStateBean state= new  OrderStateBean();
     	state.setStateNum(2);
     	bean.setState(state);
     	if(dao.sqlUpdate(bean)){
     		ArrayList<OrderBean> list=dao.sqlQuery(bean);
     		Map<String,Object> map= new HashMap<String, Object>();
     		map.put("rows", list.get(0));
     		JSONObject object= JSONObject.fromObject(map);
     		return object;
     	}
     	return null;
	}
	@Override
	public Boolean ticketReturn(OrderBean bean) {
		OrderStateBean state= new  OrderStateBean();
     	state.setStateNum(4);
     	bean.setState(state);
     	return dao.sqlUpdate(bean);
	}
	@Override
	public Object getOrderInfo(int pageNum, int pageSize, OrderBean bean) {
		OrderStateBean state= new OrderStateBean();
		state.setStateNum(1);
		bean.setState(state);
		ArrayList<OrderBean> list=dao.sqlQueryByPage(pageNum, pageSize, bean);
		Long total=dao.sqlGetTicketGetTotal(bean);
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);
		net.sf.json.JSONObject array= JSONObject.fromObject(map);
		return array;
	}

}
