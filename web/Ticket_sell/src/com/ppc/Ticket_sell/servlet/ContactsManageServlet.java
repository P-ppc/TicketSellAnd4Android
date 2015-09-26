package com.ppc.Ticket_sell.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppc.Ticket_sell.bean.ContactsBean;
import com.ppc.Ticket_sell.bean.PhoneUserBean;
import com.ppc.Ticket_sell.service.ContactsManageService;
import com.ppc.Ticket_sell.service.impl.ContactsManageServiceImpl;
import com.ppc.Ticket_sell.util.MyUtils;

/**
 * Servlet implementation class ContactsManageServlet
 */
@WebServlet("/ContactsManageServlet")
public class ContactsManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ContactsManageService service= new ContactsManageServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactsManageServlet() {
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
		String contactNo=request.getParameter("contactNo");
		String contactName=request.getParameter("contactName");
		String contactId=request.getParameter("contactId");
		ContactsBean contact= new ContactsBean();
		PhoneUserBean phoneUser= new PhoneUserBean();
		if(MyUtils.isNotNull(contactId)){
			contact.setContactId(contactId);
		}
		if(MyUtils.isNotNull(contactName)){
			contact.setContactName(contactName);
		}
		if(MyUtils.isNotNull(contactNo)){
			contact.setContactNo(Integer.parseInt(contactNo));
		}
		if(MyUtils.isNotNull(userName)){
			phoneUser.setUserName(userName);
		}
		contact.setUser(phoneUser);
		if(MyUtils.isNotNull(function)&& function.equals("query")){
			System.out.println(contact.toString());
			Object object = service.getContactsInfo(contact);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(object.toString());
		}
		if(MyUtils.isNotNull(function) && function.equals("addOrUpdate")){
		   Boolean flag=service.contactsAddOrUpdate(contact);
		   response.setContentType("text/html;charset=UTF-8");
		   System.out.println(flag.toString());
		   response.getWriter().write(flag.toString());
		}
		if(MyUtils.isNotNull(function) && function.equals("delete")){
			Boolean flag=service.contactsDelete(contact);
			response.setContentType("text/html;charset=UTF-8");
			System.out.println(flag.toString());
			response.getWriter().write(flag.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
