package com.ppc.Ticket_sell.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppc.Ticket_sell.bean.OrderBean;
import com.ppc.Ticket_sell.bean.OrderStateBean;
import com.ppc.Ticket_sell.bean.TicketBean;
import com.ppc.Ticket_sell.service.TicketGetService;
import com.ppc.Ticket_sell.service.impl.TicketGetManageServiceImpl;

/**
 * Servlet implementation class TicketReturnServlet
 */
@WebServlet("/TicketReturnServlet")
public class TicketReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TicketGetService getService=new TicketGetManageServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketReturnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getParameter("function");
		String orderNum=request.getParameter("orderNum");
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
		String date=request.getParameter("date");
		String seatNo=request.getParameter("seatNo");
		String ticketNum=request.getParameter("ticketNum");
		String passengerName=request.getParameter("passengerName");
		String passengerId=request.getParameter("passengerId");
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
			Object array=getService.returnOrderInfo(pageNo,size, bean);
			response.setContentType("text/html;charset=UTF-8");
			System.out.println(array.toString());
			response.getWriter().write(array.toString());
			
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
