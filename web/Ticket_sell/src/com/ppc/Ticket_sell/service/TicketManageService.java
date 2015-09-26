package com.ppc.Ticket_sell.service;

import com.ppc.Ticket_sell.bean.TicketBean;

public interface TicketManageService {

	public Object getTicketInfo(int pageNum,int pageSize,TicketBean bean);
	
	public Boolean ticketAddOrUpdate(TicketBean bean);
	
	public Boolean ticketDelete(TicketBean bean);
}
