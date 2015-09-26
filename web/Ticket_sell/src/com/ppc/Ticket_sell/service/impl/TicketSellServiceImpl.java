package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.OrderBean;
import com.ppc.Ticket_sell.bean.OrderStateBean;
import com.ppc.Ticket_sell.bean.ResidueTicketBean;
import com.ppc.Ticket_sell.bean.TicketBean;
import com.ppc.Ticket_sell.dao.OrderDao;
import com.ppc.Ticket_sell.dao.TicketDao;
import com.ppc.Ticket_sell.dao.impl.OrderDaoImpl;
import com.ppc.Ticket_sell.dao.impl.TicketDaoImpl;
import com.ppc.Ticket_sell.service.TicketSellService;

public class TicketSellServiceImpl implements TicketSellService {

	TicketDao tkDao= new TicketDaoImpl();
	OrderDao orderDao= new OrderDaoImpl();
	
	@Override
	public Object getResidueTicketByPage(int pageNum, int pageSize, TicketBean tkbean,OrderBean orderBean) {
		//获取符合条件的TicketBean list
		ArrayList<TicketBean> ticketList=tkDao.sqlQuerySellByPage(pageNum, pageSize, tkbean,orderBean.getDate());
		//获取所有生效且符合日期的OrderBean list
		ArrayList<OrderBean> orderList=orderDao.sqlQueryEffective(orderBean);
		ArrayList<ResidueTicketBean> residueTicket= new ArrayList<ResidueTicketBean>();
		Long total=(long) 0;
		if(orderBean.getDate()!=null && orderBean.getDate()!=""){
			//遍历  获取同一ticketNum的剩余座位量
		  residueTicket=this.residueTicket(ticketList, orderList, orderBean.getDate());
		  total=tkDao.sqlGetSellTotal(tkbean, orderBean.getDate());
		}
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows",residueTicket);
		//对象怎么接受剩余座位量？ 自己写个对象 放 ticketBean  +  剩余票数  +日期
		//转换为JSON返回
		net.sf.json.JSONObject array= JSONObject.fromObject(map);
		return array;
	}
   //返回剩余车票信息
   public ArrayList<ResidueTicketBean> residueTicket(ArrayList<TicketBean> ticketList,ArrayList<OrderBean> orderList,String date){
	   ArrayList<ResidueTicketBean> list= new ArrayList<ResidueTicketBean>();
	   for(TicketBean ticket : ticketList){
		   int residueSeat=ticket.getSeatNum();
		   for(OrderBean order : orderList){
			   if(ticket.getTicketNum().equals(order.getTicket().getTicketNum())){
				   residueSeat--;
			   }
		   }
		   ResidueTicketBean residueTicekt= new ResidueTicketBean();
           residueTicekt.setDate(date);
           residueTicekt.setTicket(ticket);
           residueTicekt.setResidueSeat(residueSeat);
           list.add(residueTicekt);
	   }
	   return list;
   }

   //要改 自动生成主键
   @Override
   public Object sellTicket(OrderBean orderBean) {
	  //获取日期，车票符合的生效的OrderBean list
	   OrderBean newBean = new OrderBean();
	   newBean.setDate(orderBean.getDate());
	   newBean.setTicket(orderBean.getTicket());
	   ArrayList<OrderBean> orderList=orderDao.sqlQueryEffective(newBean);
	  //遍历获取空的座位号
	   int seatNo=this.getSeat(orderList);
	   if(seatNo!=0){
	      orderBean.setSeatNo(seatNo);
	      OrderStateBean state= new OrderStateBean();
	      state.setStateNum(3);
	      orderBean.setState(state);
	       String key=orderDao.getSaveResultId(orderBean);
	       orderBean.setOrderNum(Integer.parseInt(key));
	       orderList=orderDao.sqlQuery(orderBean);
	       Map<String,Object> map= new HashMap<String, Object>();
	       map.put("rows",orderList.get(0));
	       JSONObject object=JSONObject.fromObject(map);
	       return object;
	   }   
	   //返回空
	   return null;
   }
   //获取空的座位号
   public int getSeat(ArrayList<OrderBean> orderList){
	   ArrayList<Integer> seatNoList=new ArrayList<Integer>();
	   if(orderList.size()==0){
		   return 1;
	   }
	   for(OrderBean order :orderList){
		   seatNoList.add(order.getSeatNo());
		   System.out.println("ticket---"+order.getTicket().toString());
	   }
	   ArrayList<Integer> seatTotalList= new ArrayList<Integer>();
	   Integer maxSeatNo=orderList.get(0).getTicket().getSeatNum();
	   System.out.println(maxSeatNo);
	   for(int i=1;i<=maxSeatNo;i++){
		   seatTotalList.add(i);
	   }
	   Collections.sort(seatNoList);
	   System.out.println("list---"+seatTotalList.size());
	   for(int i=0;i<seatTotalList.size();i++){
		   for(Integer j:seatNoList){
			   if(seatTotalList.get(i).equals(j)){
				   seatTotalList.remove(i);
			   }
		   }
	   }
	   System.out.println("list---"+seatTotalList.size());
	   if(seatTotalList.size()>0){
		   return seatTotalList.get(0);
	   }else{
	      return 0;
	   }
   }
@Override
public Object netSellTicket(OrderBean orderBean) {
	   OrderBean newBean = new OrderBean();
	   newBean.setDate(orderBean.getDate());
	   newBean.setTicket(orderBean.getTicket());
	   ArrayList<OrderBean> orderList=orderDao.sqlQueryEffective(newBean);
	  //遍历获取空的座位号
	   int seatNo=this.getSeat(orderList);
	   if(seatNo!=0){
	      orderBean.setSeatNo(seatNo);
	      OrderStateBean state= new OrderStateBean();
	      state.setStateNum(0);
	      orderBean.setState(state);
	       String key=orderDao.getSaveResultId(orderBean);
	       orderBean.setOrderNum(Integer.parseInt(key));
	       orderList=orderDao.sqlQuery(orderBean);
	       Map<String,Object> map= new HashMap<String, Object>();
	       map.put("rows",orderList);
	       JSONObject object=JSONObject.fromObject(map);
	       return object;
	   }   
	   
	   return null;
   }
@Override
public Object getOrder(OrderBean orderBean) {
	ArrayList<OrderBean> list= new ArrayList<OrderBean>();
	list=orderDao.sqlQuery(orderBean);
	Map<String,Object> map= new HashMap<String, Object>();
	map.put("rows", list);
	JSONObject object= JSONObject.fromObject(map);
	return object;
}
@Override
public Boolean cancelOrder(OrderBean orderBean) {
	try{
		orderDao.delete(orderBean);
		return true;
	}catch(Exception ex){
		ex.printStackTrace();
		return false;
	}
}
@Override
public Boolean payOrder(OrderBean orderBean) {
	OrderStateBean state= new OrderStateBean();
	state.setStateNum(1);
	orderBean.setState(state);
	return orderDao.sqlUpdate(orderBean);
}
@Override
public Object getUnpaidEffectiveOrder(OrderBean bean) {
	ArrayList<OrderBean> list=orderDao.sqlQueryPhoneUserEffectiveUnpaidOrder(bean);
	Map<String,Object> map= new HashMap<String, Object>();
	map.put("rows", list);
	JSONObject object=JSONObject.fromObject(map);
	return object;
}
@Override
public Object getResidueTicket(TicketBean tkbean, OrderBean orderBean) {
	//获取符合条件的TicketBean list
			ArrayList<TicketBean> ticketList=tkDao.sqlQuerySell( tkbean,orderBean.getDate());
			//获取所有生效且符合日期的OrderBean list
			ArrayList<OrderBean> orderList=orderDao.sqlQueryEffective(orderBean);
			ArrayList<ResidueTicketBean> residueTicket= new ArrayList<ResidueTicketBean>();
			Long total=(long) 0;
			if(orderBean.getDate()!=null && orderBean.getDate()!=""){
				//遍历  获取同一ticketNum的剩余座位量
			  residueTicket=this.residueTicket(ticketList, orderList, orderBean.getDate());
			  total=tkDao.sqlGetSellTotal(tkbean, orderBean.getDate());
			}
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("total", total);
			map.put("rows",residueTicket);
			//对象怎么接受剩余座位量？ 自己写个对象 放 ticketBean  +  剩余票数  +日期
			//转换为JSON返回
			net.sf.json.JSONObject array= JSONObject.fromObject(map);
			return array;
}
   
}
