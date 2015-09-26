package com.ppc.Ticket_sell.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.OrderBean;
import com.ppc.Ticket_sell.bean.OrderStateBean;
import com.ppc.Ticket_sell.bean.PhoneUserBean;
import com.ppc.Ticket_sell.bean.TicketBean;
import com.ppc.Ticket_sell.bean.WorkerBean;
import com.ppc.Ticket_sell.service.TicketSellService;
import com.ppc.Ticket_sell.service.impl.TicketSellServiceImpl;
import com.ppc.Ticket_sell.util.MyUtils;

/**
 * Servlet implementation class TicketSellServlet
 */
@WebServlet("/TicketSellServlet")
public class TicketSellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TicketSellService service= new TicketSellServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketSellServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("UTF-8");
	   String function=request.getParameter("function");
	   String orderNum=request.getParameter("orderNum");
       String ticketNum=request.getParameter("ticketNum");
       String start_station=request.getParameter("start_station");
       String end_station=request.getParameter("end_station");
       String date=request.getParameter("date");
       String stateNum=request.getParameter("stateNum");
       String passengerName=request.getParameter("passengerName");
       String passengerId=request.getParameter("passengerId");
       String phoneUserName=request.getParameter("userName");
       String workerName=request.getParameter("workerName");
       String pageNum=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		int pageNo=1;
		if(pageNum!=null && !pageNum.trim().equals("")){
			pageNo=Integer.parseInt(pageNum);
		}
		int size=10;
		if(pageSize!=null && !pageSize.trim().equals("")){
		    size=Integer.parseInt(pageSize);
		}
       if(function!=null && function.equals("query")){
    	   OrderBean bean= new OrderBean();
    	   TicketBean tbean= new TicketBean();
    	   if(start_station!=null && start_station!=""){
    		   tbean.setStart_station(start_station);
    	   }
    	   if(end_station!=null && end_station!=""){
    		   tbean.setEnd_station(end_station);
    	   }
    	   if(date!=null && date!=""){
    		   bean.setDate(date);
    	   }
    	   bean.setTicket(tbean);
    	   Object array =service.getResidueTicketByPage(pageNo, size, tbean, bean);
    	   response.setContentType("text/html;charset=UTF-8");
    	   response.getWriter().write(array.toString());
    	   System.out.println(array.toString());
       }
       if(function!=null && function.equals("sell")){
    	   TicketBean tkBean= new TicketBean();
    	   OrderBean orderBean= new OrderBean();
    	   WorkerBean worker= new WorkerBean();
    	   if(ticketNum!=null&& ticketNum!=""){
    		   tkBean.setTicketNum(Integer.parseInt(ticketNum));
    	   }
    	   if(date!=null && date!=""){
    		   orderBean.setDate(date);
    	   }
    	   if(tkBean!=null && tkBean.getTicketNum()!=null){
    		   orderBean.setTicket(tkBean);
    	   }
    	   if(workerName!=null && workerName!=""){
    		   worker.setWorkerName(workerName);
    		   orderBean.setWorker(worker);
    		   System.out.println("workerName---"+workerName);
    	   }
    	  Object object=service.sellTicket(orderBean);
    	   if(object==null){
    		   Map<String,Object> map= new HashMap<String, Object>();
    		   map.put("data", "fail");
    		   JSONObject result= JSONObject.fromObject(map);
    		   response.setContentType("text/html;charset=UTF-8");
        	   response.getWriter().write(result.toString());
    	   }else{
    		   response.setContentType("text/html;charset=UTF-8");
        	   response.getWriter().write(object.toString());
    	   }
       }
       if(MyUtils.isNotNull(function)&& function.equals("netSell")){
    	   TicketBean tkBean= new TicketBean();
    	   OrderBean orderBean= new OrderBean();
    	   PhoneUserBean user= new PhoneUserBean();
    	   if(MyUtils.isNotNull(ticketNum)){
    		   tkBean.setTicketNum(Integer.parseInt(ticketNum));
    		   orderBean.setTicket(tkBean);
    	   }
    	   if(MyUtils.isNotNull(date)){
    		   orderBean.setDate(date);
    	   }
    	   if(MyUtils.isNotNull(phoneUserName)){
    		   user.setUserName(phoneUserName);
    		   orderBean.setUser(user);
    	   }
    	   if(MyUtils.isNotNull(passengerId)){
    		   orderBean.setPassengerId(passengerId);
    	   }
    	   if(MyUtils.isNotNull(passengerName)){
    		   orderBean.setPassengerName(passengerName);
    	   }
    	   Date now = new Date();  
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
           String orderTime = format.format(now); 
    	   orderBean.setOrderTime(orderTime);
    	   Object object=service.netSellTicket(orderBean);
    	   if(object==null){
    		   response.setContentType("text/html;charset=UTF-8");
        	   response.getWriter().write("fail");
    	   }
    	   else{
    		   response.setContentType("text/html;charset=UTF-8");
        	   response.getWriter().write(object.toString());
    	   }
    	   
    	  
		   
       }
       if(function!=null && function.equals("orderQuery")){
    	   OrderBean orderBean= new OrderBean();
    	   PhoneUserBean user= new PhoneUserBean();
    	   OrderStateBean state= new OrderStateBean();
    	   if(MyUtils.isNotNull(phoneUserName) && MyUtils.isNotNull(stateNum)){
    		   user.setUserName(phoneUserName);
    		   state.setStateNum(Integer.parseInt(stateNum));
    		   orderBean.setUser(user);
    		   orderBean.setState(state);
    		   if(state.getStateNum().equals(0)){
    			   Object object=service.getUnpaidEffectiveOrder(orderBean);
    			   response.setContentType("text/html;charset=UTF-8");
        		   response.getWriter().write(object.toString());
    		   }else{
    			   Object object=service.getOrder(orderBean);
        		   System.out.println(object.toString());
            	   response.setContentType("text/html;charset=UTF-8");
        		   response.getWriter().write(object.toString());
    		   }
    		 
    	   }
       }
       if(function!=null && function.equals("cancelOrder")){
    	   OrderBean orderBean = new  OrderBean();
    	   if(MyUtils.isNotNull(orderNum)){
    		   orderBean.setOrderNum(Integer.parseInt(orderNum));
    		   Object object = service.cancelOrder(orderBean);
    		   response.setContentType("text/html;charset=UTF-8");
    		   response.getWriter().write(object.toString());
    	   }
       }
       if(function!=null && function.equals("payOrder")){
    	   OrderBean orderBean = new OrderBean();
    	   if(MyUtils.isNotNull(orderNum)){
    		   orderBean.setOrderNum(Integer.parseInt(orderNum));
    		   Object object=service.payOrder(orderBean);
    		   System.out.println("pay------"+object.toString());
    		   response.setContentType("text/html;charset=UTF-8");
    		   response.getWriter().write(object.toString());
    	   }
       }
       if(function!=null && function.equals("netQuery")){
    	   OrderBean bean= new OrderBean();
    	   TicketBean tbean= new TicketBean();
    	   if(start_station!=null && start_station!=""){
    		   tbean.setStart_station(start_station);
    	   }
    	   if(end_station!=null && end_station!=""){
    		   tbean.setEnd_station(end_station);
    	   }
    	   if(date!=null && date!=""){
    		   bean.setDate(date);
    	   }
    	   bean.setTicket(tbean);
    	   Object array =service.getResidueTicket(tbean, bean);
    	   response.setContentType("text/html;charset=UTF-8");
    	   response.getWriter().write(array.toString());
    	   System.out.println(array.toString());
       }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
