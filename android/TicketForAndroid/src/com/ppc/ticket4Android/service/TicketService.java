package com.ppc.ticket4Android.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.ppc.ticket4Android.bean.OrderBean;
import com.ppc.ticket4Android.bean.ResidueTicketBean;
import com.ppc.ticket4Android.bean.TicketBean;

public interface TicketService
	{
		/**
		 * 查询车票信息
		 * @param bean 车票信息
		 * @return 
		 */
        public ArrayList<TicketBean> getTickets(TicketBean bean);
        /**
         * 查询 车票信息 并格式化为ArrayList<HashMap<String,Object>>
         * @param bean
         * @return
         */
        public ArrayList<HashMap<String,Object>> getTicketsListItem(TicketBean bean);
        /**
         * 查询剩余车票信息
         * @param 订单信息
         * @return
         */
        public ArrayList<ResidueTicketBean> getResidueTickets(OrderBean bean);
        /**
         * 查询剩余车票信息 并格式化为ArrayList<HashMap<String,Object>>
         * @param 订单信息
         * @return
         */
        public ArrayList<HashMap<String,Object>> getResidueTicketsListItem(OrderBean bean);
        
        /**
         * function: 购买车票
         * @param bean
         * @return 返回座位号 是0的话就是购买失败
         */
        public ArrayList<HashMap<String, Object>> buyTicket(OrderBean bean);
        
        
        public ArrayList<HashMap<String,Object>>  getUserEffectiveOrder(OrderBean bean);
        
        
        public Boolean cancelOrder(OrderBean bean);
        
        public Boolean payOrder(OrderBean bean);
        
	}
