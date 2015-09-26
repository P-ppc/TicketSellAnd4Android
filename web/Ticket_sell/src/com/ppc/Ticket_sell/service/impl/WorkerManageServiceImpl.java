package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.WorkerBean;
import com.ppc.Ticket_sell.dao.WorkerDao;
import com.ppc.Ticket_sell.dao.impl.WorkerDaoImpl;
import com.ppc.Ticket_sell.service.WorkerManageService;


public class WorkerManageServiceImpl  implements WorkerManageService {
   WorkerDao dao= new WorkerDaoImpl();
	
	@Override
	public Boolean Login(WorkerBean bean) {
		Boolean flag=false;
		WorkerBean newBean = new WorkerBean();
		if(bean.getWorkerName()!=null && bean.getWorkerName()!=""){
		 newBean=dao.sqlFindById(bean.getWorkerName().toString());
		}
        if(newBean!=null){
		 if(newBean.getPassword()!=null && newBean.getPassword().toString().trim().equals(bean.getPassword())){
			flag=true;
		 }
        } 
		return flag;
	}

	@Override
	public Object getWorkerInfo(int pageNum, int pageSize, WorkerBean bean) {
	    
		ArrayList<WorkerBean> list=dao.sqlQuery(pageNum, pageSize, bean);
		Long total=dao.sqlGetTotal(bean);
		Map<String , Object> map= new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		net.sf.json.JSONObject array=JSONObject.fromObject(map);
		return array;
	}

	@Override
	public Boolean workerAddOrUpdate(WorkerBean bean) {
		Boolean flag=false;
		try{
			dao.savaOrUpdate(bean);
			flag=true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return flag;
	}

	@Override
	public Boolean workerDelete(WorkerBean bean) {
		Boolean flag=false;
		try{
			dao.delete(bean);
			flag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public WorkerBean getWorker(WorkerBean bean) {
		WorkerBean result= new WorkerBean();
		result=dao.sqlFindById(bean.getWorkerName());
		return result;
	}

	
}
