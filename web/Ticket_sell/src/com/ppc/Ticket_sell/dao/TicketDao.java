package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.TicketBean;

public interface TicketDao extends BaseDao{
	
   /**
    *    
    * @param pageNum
    * @param pageSize
    * @param bean
    * @return
    */
   public ArrayList<TicketBean> sqlQueryAll(int pageNum,int pageSize,TicketBean bean);
   
   public ArrayList<TicketBean> sqlQuerySellByPage(int pageNum,int pageSize,TicketBean bean,String date);
   
   public Long sqlGetTotal(TicketBean bean);
   
   public TicketBean sqlFindById(String id);
   
   public Long sqlGetSellTotal(TicketBean bean,String date);
 
   public ArrayList<TicketBean> sqlQuerySell(TicketBean bean,String date);
   
}
