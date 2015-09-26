package com.ppc.Ticket_sell.service;

import com.ppc.Ticket_sell.bean.OrderBean;

public interface TicketGetService {

	public Object returnOrderInfo(int pageNum,int pageSize,OrderBean bean);
	
	public Object getOrderInfo(int pageNum,int pageSize,OrderBean bean);
	
	public Boolean OrderDelete(OrderBean bean);
	
	public Object ticketGet(OrderBean bean);
	
	public Boolean ticketReturn(OrderBean bean);
}
