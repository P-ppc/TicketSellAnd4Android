package com.ppc.Ticket_sell.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppc.Ticket_sell.bean.JurisdictionBean;
import com.ppc.Ticket_sell.service.JdManageService;
import com.ppc.Ticket_sell.service.impl.JdManageServiceImpl;

/**
 * Servlet implementation class JdManageServlet
 */
@WebServlet("/JdManageServlet")
public class JdManageServlet extends HttpServlet {
	
	JdManageService service = new JdManageServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JdManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function =request.getParameter("function");
		String jdNum=request.getParameter("jdNum");
		String jdName=request.getParameter("jdName");
		String pageNum=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		System.out.println(function);
		System.out.println(jdNum);
		System.out.println(jdName);
		int pageNo=1;
		if(pageNum!=null && !pageNum.trim().equals("")){
			pageNo=Integer.parseInt(pageNum);
		}
		int size=10;
		if(pageSize!=null && !pageSize.trim().equals("")){
		    size=Integer.parseInt(pageSize);
		}
		
		if(function!=null && function.equals("query")){
			JurisdictionBean bean = new JurisdictionBean();
			if(jdNum!=null && jdNum!=""){
				bean.setJurisdictionNum(Integer.parseInt(jdNum));
			}
			bean.setJurisdictionName(jdName);
			Object array=service.getJdInfo(pageNo, size, bean);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(array.toString());
			System.out.println(array.toString());
		}
		if(function!=null && function.equals("addOrUpdate")){
			JurisdictionBean bean = new JurisdictionBean();
			if(jdNum!=null && jdNum!=""){
				bean.setJurisdictionNum(Integer.parseInt(jdNum));
			}
			bean.setJurisdictionName(jdName);
			Boolean flag=service.jdAddOrUpdate(bean);
		}
		if(function!=null && function.equals("delete")){
			JurisdictionBean bean = new JurisdictionBean();
			if(jdNum!=null && jdNum!=""){
				bean.setJurisdictionNum(Integer.parseInt(jdNum));
			}
			bean.setJurisdictionName(jdName);
			Boolean flag=service.jdDelete(bean);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response);
	}

}
