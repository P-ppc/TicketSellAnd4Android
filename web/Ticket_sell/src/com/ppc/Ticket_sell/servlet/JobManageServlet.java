package com.ppc.Ticket_sell.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppc.Ticket_sell.bean.JobBean;
import com.ppc.Ticket_sell.service.JobManageService;
import com.ppc.Ticket_sell.service.impl.JobManageServiceImpl;

/**
 * Servlet implementation class JobManageServlet
 */
@WebServlet("/JobManageServlet")
public class JobManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	JobManageService service= new JobManageServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String function =request.getParameter("function");
		System.out.println("function:"+function);
		String jobNum=request.getParameter("jobNum");
		String jobName=request.getParameter("jobName");
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
		 JobBean bean = new JobBean();
		 if(jobNum!=null && jobNum!=""){
		    bean.setJobNum(Integer.parseInt(jobNum));
		 }
		 bean.setJobName(jobName);
		 Object jobInfo= service.getJobInfo(pageNo, size, bean);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(jobInfo.toString());
		}
		if(function!=null && function.equals("addOrUpdate")){
		  JobBean bean = new JobBean();
		  if(jobNum!=null && jobNum!=""){
			bean.setJobNum(Integer.parseInt(jobNum));
		  }
		    bean.setJobName(jobName);
		  service.jobAddOrUpdate(bean);  
		}
		if(function!=null && function.equals("delete")){
			  JobBean bean = new JobBean();
			  if(jobNum!=null && jobNum!=""){
				bean.setJobNum(Integer.parseInt(jobNum));
			  }
			   bean.setJobName(jobName);
			  service.jobDelete(bean);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
