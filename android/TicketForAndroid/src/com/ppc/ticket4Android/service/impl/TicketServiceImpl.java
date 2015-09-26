package com.ppc.ticket4Android.service.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.ppc.ticket4Android.bean.OrderBean;
import com.ppc.ticket4Android.bean.ResidueTicketBean;
import com.ppc.ticket4Android.bean.TicketBean;
import com.ppc.ticket4Android.service.TicketService;
import com.ppc.ticket4Android.util.Contstant;
import com.ppc.ticket4Android.util.HttpUtils;
import com.ppc.ticket4Android.util.MyUtils;

public class TicketServiceImpl implements TicketService
	{
	
	 @Override
	 public ArrayList<TicketBean> getTickets(TicketBean bean)
		 {   
		     return null;
		 }
	@Override
	public ArrayList<HashMap<String, Object>> getTicketsListItem(TicketBean bean)
		{
            ArrayList<HashMap<String, Object>>	list= new ArrayList<HashMap<String,Object>>();	
		    HashMap<String, String> params= new HashMap<String, String>();
	        params.put("function", "query");
	        if(bean.getStart_station()!=null && bean.getStart_station()!=""){
	            params.put("start_station", bean.getStart_station());
	        }
	        if(bean.getEnd_station()!=null && bean.getEnd_station()!=""){
	            params.put("end_station", bean.getEnd_station());
	        }
	        String uri=Contstant.BASE_URI+"ticketInfo";
	        String resultJson;
			try {
				resultJson = HttpUtils.submitPostData(params,HTTP.UTF_8, uri);
			} catch (ConnectTimeoutException e1) {
				e1.printStackTrace();
				return null;
			} catch (SocketTimeoutException e1) {
				e1.printStackTrace();
				return null;
			}
	        try {
				ArrayList<Object> tickets=HttpUtils.json2ArrayListByRows(resultJson.replaceAll(" ", ""));
				for(int i=0;i<tickets.size();i++){
					 HashMap<String, Object> map= new HashMap<String, Object>();
					 map=(HashMap<String, Object>) tickets.get(i);
					 list.add(map);
				}
				return list;
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	        
		}

	@Override
	public ArrayList<ResidueTicketBean> getResidueTickets(OrderBean bean) {
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getResidueTicketsListItem(
			OrderBean bean) {
		ArrayList<HashMap<String, Object>>	list= new ArrayList<HashMap<String,Object>>();	
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "query");
		if(bean.getTicket().getStart_station()!=null && bean.getTicket().getStart_station()!=""){
		  params.put("start_station",bean.getTicket().getStart_station());
		}
		if(bean.getTicket().getEnd_station()!=null && bean.getTicket().getEnd_station()!=""){
			params.put("end_station",bean.getTicket().getEnd_station());
		}
		if(bean.getDate()!=null && bean.getDate()!=""){
			params.put("date", bean.getDate());
		}
		String uri=Contstant.BASE_URI+"ticketSell";
		String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e1) {
			e1.printStackTrace();
			return null;
		} catch (SocketTimeoutException e1) {
			e1.printStackTrace();
			return null;
		}
		ArrayList<Object> residueTickets;
		try {
			residueTickets = HttpUtils.json2ArrayListByRows(resultJson);
			for(int i=0;i<residueTickets.size();i++){
				 HashMap<String, Object> map= new HashMap<String, Object>();
				 map=(HashMap<String, Object>) residueTickets.get(i);
				 HashMap<String, Object> ticket= (HashMap<String, Object>) map.get("ticket");
				 HashMap<String, Object> newmap= new HashMap<String, Object>();
				 // 可以遍历
				 newmap.put("date",map.get("date").toString().trim());
				 newmap.put("residueSeat", map.get("residueSeat").toString().trim());
				 newmap.put("start_station",ticket.get("start_station").toString().trim());
				 newmap.put("end_station",ticket.get("end_station").toString().trim());
				 newmap.put("price",ticket.get("price").toString().trim());
				 newmap.put("time",ticket.get("time").toString().trim().substring(0, 5));
				 newmap.put("ticketNum", ticket.get("ticketNum").toString().trim());
				 newmap.put("motorcoach_number",ticket.get("motorcoach_number").toString().trim());
				 newmap.put("seatNum",ticket.get("seatNum").toString().trim());
				 
				 list.add(newmap);
				}
			return list;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> buyTicket(OrderBean bean) {
		ArrayList<HashMap<String, Object>>	list= new ArrayList<HashMap<String,Object>>();	
		String uri=Contstant.BASE_URI+"ticketSell";
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "netSell");
		if(MyUtils.isNotNull(bean.getDate())){
			params.put("date", bean.getDate());
		}
		if(MyUtils.isNotNull(bean.getTicket().getTicketNum())){
			params.put("ticketNum",bean.getTicket().getTicketNum().toString());
		}
		if(MyUtils.isNotNull(bean.getUser().getUserName())){
			params.put("userName", bean.getUser().getUserName());
		}
		if(MyUtils.isNotNull(bean.getPassengerId())){
			params.put("passengerId", bean.getPassengerId());
		}
		if(MyUtils.isNotNull(bean.getPassengerName())){
			params.put("passengerName", bean.getPassengerName());
		}
		String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e1) {
			e1.printStackTrace();
			return null;
		} catch (SocketTimeoutException e1) {
			e1.printStackTrace();
			return null;
		}
		ArrayList<Object> order;
		try {
			if(resultJson.equals("fail")){
				return null;
			}
			else{
				order = HttpUtils.json2ArrayListByRows(resultJson);
				for(int i=0;i<order.size();i++){
					HashMap<String, Object> map= new HashMap<String, Object>();
					map=(HashMap<String, Object>) order.get(i);
					list.add(map);
				}
				return list;
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getUserEffectiveOrder(OrderBean bean) {
		ArrayList<HashMap<String, Object>>	list= new ArrayList<HashMap<String,Object>>();	
		String uri=Contstant.BASE_URI+"ticketSell";
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "orderQuery");
		
		if(MyUtils.isNotNull(bean.getUser().getUserName())){
			params.put("userName", bean.getUser().getUserName());
		}
		if(MyUtils.isNotNull(bean.getState().getStateNum())){
			params.put("stateNum", bean.getState().getStateNum().toString());
		}
//		if(MyUtils.isNotNull(bean.getState().getStateNum())){
//			params.put("stateNum",bean.getState().getStateNum().toString());
//		}
		String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e1) {
			e1.printStackTrace();
			return null;
		} catch (SocketTimeoutException e1) {
			e1.printStackTrace();
			return null;
		}
		ArrayList<Object> userOrder;
		try {
			userOrder = HttpUtils.json2ArrayListByRows(resultJson);
			for(int i=0;i<userOrder.size();i++){
				HashMap<String, Object> map= new HashMap<String, Object>();
				map=(HashMap<String, Object>) userOrder.get(i);
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public Boolean cancelOrder(OrderBean bean) {
		String uri=Contstant.BASE_URI+"ticketSell";
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "cancelOrder");
		if(MyUtils.isNotNull(bean.getOrderNum().toString())){
			params.put("orderNum",bean.getOrderNum().toString());
		}
		try {
			String resultJson=HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
			if(resultJson.equals("true")){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return false;
	}
	@Override
	public Boolean payOrder(OrderBean bean) {
		String uri=Contstant.BASE_URI+"ticketSell";
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "payOrder");
		if(MyUtils.isNotNull(bean.getOrderNum().toString())){
			params.put("orderNum",bean.getOrderNum().toString());
		}
		try {
			String resultJson=HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
			if(resultJson.equals("true")){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return false;
	}
}
