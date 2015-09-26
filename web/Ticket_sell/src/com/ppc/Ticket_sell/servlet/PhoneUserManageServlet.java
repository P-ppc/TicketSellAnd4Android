package com.ppc.Ticket_sell.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppc.Ticket_sell.bean.PhoneUserBean;
import com.ppc.Ticket_sell.service.PhoneUserManageService;
import com.ppc.Ticket_sell.service.impl.PhoneUserManageServiceImpl;
import com.ppc.Ticket_sell.util.MyUtils;

/**
 * Servlet implementation class PhoneUserManageServlet
 */
@WebServlet("/PhoneUserManageServlet")
public class PhoneUserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	PhoneUserManageService service= new PhoneUserManageServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoneUserManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String function=request.getParameter("function");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
	    PhoneUserBean bean = new PhoneUserBean();
	    if(MyUtils.isNotNull(userName)){
	    	bean.setUserName(userName);
	    }
	    if(MyUtils.isNotNull(password)){
	    	bean.setPassword(password);
	    }
	    if(MyUtils.isNotNull(tel)){
	    	bean.setTel(Integer.parseInt(tel));
	    }
	    if(MyUtils.isNotNull(email)){
	    	bean.setEmail(email);
	    }
	    if(MyUtils.isNotNull(function) && function.equals("query")){
	    	 System.out.println(bean.toString());
	    	 Object object = service.getPhoneUserInfo(bean);
	    	 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().write(object.toString());
	    }
	    if(MyUtils.isNotNull(function) && function.equals("register")){
	    	Object object= service.register(bean);
	    	response.setContentType("text/html;charset=UTF-8");
	    	response.getWriter().write(object.toString());
	    }
	    if(MyUtils.isNotNull(function) && function.equals("resetPassword")){
	    	Object object= service.resetPassword(bean);
	    	response.setContentType("text/html;charset=UTF-8");
	    	response.getWriter().write(object.toString());
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
