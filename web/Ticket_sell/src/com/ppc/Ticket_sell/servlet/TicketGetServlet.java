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
import com.ppc.Ticket_sell.bean.TicketBean;
import com.ppc.Ticket_sell.bean.WorkerBean;
import com.ppc.Ticket_sell.service.TicketGetService;
import com.ppc.Ticket_sell.service.impl.TicketGetManageServiceImpl;

/**
 * Servlet implementation class TicketSellManageServlet
 */
@WebServlet("/TicketSellManageServlet")
public class TicketGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TicketGetService getService=new TicketGetManageServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getParameter("function");
		String orderNum=request.getParameter("orderNum");
		String date=request.getParameter("date");
		String seatNo=request.getParameter("seatNo");
		String ticketNum=request.getParameter("ticketNum");
		String passengerName=request.getParameter("passengerName");
		String passengerId=request.getParameter("passengerId");
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
			if(orderNum!=null && orderNum!=""){
				bean.setOrderNum(Integer.parseInt(orderNum));
			}
			if(passengerName!=null && passengerName!=""){
				bean.setPassengerName(passengerName);
			}
			if(passengerId!=null && passengerId!=""){
				bean.setPassengerId(passengerId);
			}
			OrderStateBean state= new OrderStateBean();
			state.setStateNum(1);
			bean.setState(state);
			Object array=getService.getOrderInfo(pageNo,size, bean);
			response.setContentType("text/html;charset=UTF-8");
			System.out.println(array.toString());
			response.getWriter().write(array.toString());
		}
		if(function!=null && function.equals("getTicket")){
			OrderBean bean= new OrderBean();
			TicketBean tkBean= new TicketBean();
			if(orderNum!=null && orderNum!=""){
				bean.setOrderNum(Integer.parseInt(orderNum));
			}
			if(date!=null && date!=""){
				bean.setDate(date);
			}
			if(seatNo!=null && seatNo!=""){
				bean.setSeatNo(Integer.parseInt(seatNo));
			}
			if(ticketNum!=null && ticketNum!=""){
				tkBean.setTicketNum(Integer.parseInt(ticketNum));
			}
			if(tkBean!=null && tkBean.getTicketNum()!=null){
				bean.setTicket(tkBean);
			}
			if(workerName!=null && workerName!=""){
				WorkerBean worker= new WorkerBean();
				worker.setWorkerName(workerName);
				bean.setWorker(worker);
			}
            Object object=getService.ticketGet(bean);
            if(object!=null){
               response.setContentType("text/html;charset=UTF-8");
           	   response.getWriter().write(object.toString());
            }else{
               Map<String,Object> map= new HashMap<String, Object>();
      		   map.put("data", "fail");
      		   JSONObject result= JSONObject.fromObject(map);
      		   response.setContentType("text/html;charset=UTF-8");
          	   response.getWriter().write(result.toString());
            }
		}
		if(function!=null && function.equals("ticketReturn")){
			OrderBean bean= new OrderBean();
			TicketBean tkBean= new TicketBean();
			if(orderNum!=null && orderNum!=""){
				bean.setOrderNum(Integer.parseInt(orderNum));
			}if(date!=null && date!=""){
				bean.setDate(date);
			}
			if(seatNo!=null && seatNo!=""){
				bean.setSeatNo(Integer.parseInt(seatNo));
			}
			if(ticketNum!=null && ticketNum!=""){
				tkBean.setTicketNum(Integer.parseInt(ticketNum));
			}
			if(tkBean!=null && tkBean.getTicketNum()!=null){
				bean.setTicket(tkBean);
			}
			getService.ticketReturn(bean);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
