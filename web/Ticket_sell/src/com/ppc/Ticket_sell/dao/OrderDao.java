package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.OrderBean;

public interface OrderDao extends BaseDao {

	public ArrayList<OrderBean> sqlQueryByPage(int pageNum,int pageSize,OrderBean bean);
	
	public Long sqlGetTotal(OrderBean bean);
	
	public ArrayList<OrderBean> sqlQueryEffective(OrderBean bean); 
	
	public ArrayList<OrderBean> sqlQuery(OrderBean bean);
	
	public Boolean sqlUpdate(OrderBean bean);
	
	public ArrayList<OrderBean> sqlQueryPhoneUserEffectiveUnpaidOrder(OrderBean bean);
	
	public ArrayList<OrderBean> sqlTicketRetrunQueryByPage(int pageNum,int pageSize,OrderBean bean);
	
	public Long sqlTicketReturnQueryTotal(OrderBean bean);
	
	public Long sqlGetTicketGetTotal(OrderBean bean);
	
 }
