package com.ppc.Ticket_sell.service;


import com.ppc.Ticket_sell.bean.OrderBean;
import com.ppc.Ticket_sell.bean.TicketBean;

public interface TicketSellService {
   
	public Object getResidueTicketByPage(int pageNum,int pageSize,TicketBean tkbean,OrderBean orderBean);
	
	public Object sellTicket(OrderBean orderBean);
	
	//返回
	public Object netSellTicket(OrderBean orderBean);
	
	public Object getOrder(OrderBean orderBean);
	
	public Boolean cancelOrder(OrderBean orderBean);
	
	public Boolean payOrder(OrderBean orderBean);
	
	public Object getUnpaidEffectiveOrder(OrderBean bean);
	
	public Object getResidueTicket(TicketBean tkbean,OrderBean orderBean);
}
