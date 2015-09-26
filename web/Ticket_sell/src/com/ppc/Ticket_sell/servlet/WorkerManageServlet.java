package com.ppc.Ticket_sell.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.JobBean;
import com.ppc.Ticket_sell.bean.JurisdictionBean;
import com.ppc.Ticket_sell.bean.WorkerBean;
import com.ppc.Ticket_sell.service.WorkerManageService;
import com.ppc.Ticket_sell.service.impl.WorkerManageServiceImpl;

/**
 * Servlet implementation class WorkerManageServlet
 */
@WebServlet("/WorkerManageServlet")
public class WorkerManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	WorkerManageService service=new  WorkerManageServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String function=request.getParameter("function");
		String workerName= request.getParameter("workerName");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String jobNum=request.getParameter("jobNum");
		String jobName=request.getParameter("jobName");
		String jdNum=request.getParameter("jdNum");
		String jdName=request.getParameter("jdName");
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
		System.out.println(function);
		if(function !=null && function.equals("redirect")){
			WorkerBean bean= new WorkerBean();
			bean.setWorkerName(workerName);
			bean.setPassword(password);
			Boolean flag=service.Login(bean);
			if(flag==true){
				
				WorkerBean worker=service.getWorker(bean);
				HttpSession session=request.getSession();
				session.setAttribute("worker", worker);
				response.sendRedirect("main.jsp");
			}
		}
		
		if(function!=null && function.equals("login"))
		{
			WorkerBean bean= new WorkerBean();
			bean.setWorkerName(workerName);
			bean.setPassword(password);
			System.out.println(bean.toString());
			Boolean flag=service.Login(bean);
			System.out.println(flag.toString());
			Map<String,String> map= new HashMap<String, String>();
			
			if(flag==true){
				map.put("data", "success");
				
				
			}else{
				map.put("data", "fail");
			}
			JSONObject data=JSONObject.fromObject(map);
			response.getWriter().write(data.toString());
			
            
		}
		if(function!=null && function.equals("query")){
			WorkerBean bean= new WorkerBean();
			bean.setWorkerName(workerName);
			bean.setPassword(password);
			bean.setName(name);
			if(tel!=null &&tel!=""){
				bean.setTel(Long.parseLong(tel));
			}
			JurisdictionBean jdBean=new JurisdictionBean();
			if(jdNum!=null && jdNum!=""){
			  jdBean.setJurisdictionNum(Integer.parseInt(jdNum));
			}
			if(jdName!=null && jdName!=""){
				jdBean.setJurisdictionName(jdName);
			}
			JobBean jobBean= new JobBean();
			if(jobNum!=null && jobNum!=""){
				jobBean.setJobNum(Integer.parseInt(jobNum));
			}
			
			bean.setJurisdiction(jdBean);
			bean.setJob(jobBean);
			Object array= service.getWorkerInfo(pageNo,size, bean);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(array.toString());
		}
		if(function!=null && function.equals("addOrUpdate")){
			WorkerBean bean= new WorkerBean();
			bean.setWorkerName(workerName);
			bean.setPassword(password);
			bean.setName(name);
			if(tel!=null &&tel!=""){
				bean.setTel(Long.parseLong(tel));
			}
			JurisdictionBean jdBean=new JurisdictionBean();
			if(jdNum!=null && jdNum!=""){
			  jdBean.setJurisdictionNum(Integer.parseInt(jdNum));
			}
			if(jdName!=null && jdName!=""){
				jdBean.setJurisdictionName(jdName);
			}
			JobBean jobBean= new JobBean();
			if(jobNum!=null && jobNum!=""){
				jobBean.setJobNum(Integer.parseInt(jobNum));
			}
			if(jobName!=null && jobName!=""){
				jobBean.setJobName(jobName);
			}
			bean.setJurisdiction(jdBean);
			bean.setJob(jobBean);
			Boolean flag=service.workerAddOrUpdate(bean);
		}
		if(function!=null && function.equals("delete")){
			WorkerBean bean= new WorkerBean();
			if(workerName!=null && workerName!=""){
				bean.setWorkerName(workerName);
			}
			Boolean flag=service.workerDelete(bean);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
