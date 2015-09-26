package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.TicketBean;
import com.ppc.Ticket_sell.dao.TicketDao;
import com.ppc.Ticket_sell.dao.impl.TicketDaoImpl;
import com.ppc.Ticket_sell.service.TicketManageService;

public class TicketManageServiceImpl implements TicketManageService {

    TicketDao ticketDao= new TicketDaoImpl();
	@Override
	public Object getTicketInfo(int pageNum, int pageSize,TicketBean bean) {
		
		
		System.out.println("-----"+bean.toString());
		ArrayList<TicketBean> list= ticketDao.sqlQueryAll(pageNum, pageSize, bean);
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		net.sf.json.JSONObject array= JSONObject.fromObject(map);
		return array;
		
	}
	@Override
	public Boolean ticketAddOrUpdate(TicketBean bean) {
		Boolean flag= false;
		   ticketDao.savaOrUpdate(bean);
		   flag=true;
		return flag;
		
	}
	@Override
	public Boolean ticketDelete(TicketBean bean) {
		Boolean flag= false;
		try{
		  ticketDao.delete(bean);
		   flag=true;
		}catch(Exception  ex)
		{
			ex.printStackTrace();
		}
		return flag;
	}

}
