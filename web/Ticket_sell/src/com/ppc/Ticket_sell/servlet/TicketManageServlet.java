package com.ppc.Ticket_sell.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppc.Ticket_sell.bean.TicketBean;
import com.ppc.Ticket_sell.service.TicketManageService;
import com.ppc.Ticket_sell.service.impl.TicketManageServiceImpl;

/**
 * Servlet implementation class TicketManageServlet
 */
@WebServlet("/TicketManageServlet")
public class TicketManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	TicketManageService ticketManage= new TicketManageServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TicketBean bean= new TicketBean();
		String function =request.getParameter("function");
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
		  String ticketNum=request.getParameter("ticketNum");
		  if(ticketNum!=null &&ticketNum!=""){
		   bean.setTicketNum(Integer.parseInt(ticketNum));
		  }
		  String start_station=request.getParameter("start_station");
		  if(start_station!=null && start_station!=""){
			bean.setStart_station(start_station);
		  }
		  String end_station=request.getParameter("end_station");
		  if(end_station!=null && end_station!=""){
			bean.setEnd_station(end_station);
		  }
		 System.out.println(bean);
		 Object ob=ticketManage.getTicketInfo(pageNo,size,bean);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(ob.toString());
		}
		if(function!=null && function.equals("addOrUpdate"))
		{
			String ticketNum=request.getParameter("ticketNum");
			if(ticketNum!=null &&ticketNum!=""){
			bean.setTicketNum(Integer.parseInt(ticketNum));
			}
			bean.setStart_station(request.getParameter("start_station").trim());
			bean.setEnd_station(request.getParameter("end_station").trim());
			bean.setPrice(Double.parseDouble(request.getParameter("price").trim()));
			bean.setTime(request.getParameter("time").trim());
			bean.setMotorcoach_number(request.getParameter("motorcoach_number"));
			bean.setSeatNum(Integer.parseInt(request.getParameter("seatNum").trim()));
			System.out.println(bean.toString());
			Boolean flag=ticketManage.ticketAddOrUpdate(bean);
		}
		
		if(function!=null && function.equals("delete"))
		{
			bean.setTicketNum(Integer.parseInt(request.getParameter("ticketNum")));
			ticketManage.ticketDelete(bean);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
