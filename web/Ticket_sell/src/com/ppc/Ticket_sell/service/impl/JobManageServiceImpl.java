package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.JobBean;
import com.ppc.Ticket_sell.dao.JobDao;
import com.ppc.Ticket_sell.dao.impl.JobDaoImpl;
import com.ppc.Ticket_sell.service.JobManageService;

public class JobManageServiceImpl implements JobManageService {

    JobDao dao= new JobDaoImpl();
	@Override
	public Object getJobInfo(int pageNum, int pageSize, JobBean bean) {
	    ArrayList<JobBean> list =dao.sqlQuery(pageNum, pageSize, bean);
	    Long total=dao.sqlGetTotal(bean);
	    Map<String,Object> map= new HashMap<String, Object>();
	    map.put("total", total);
	    map.put("rows", list);
	    net.sf.json.JSONObject array=JSONObject.fromObject(map);
		return array;
	}
	@Override
	public Boolean jobAddOrUpdate(JobBean bean) {
		Boolean flag=false;
		try{
			dao.savaOrUpdate(bean);
			flag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}
	@Override
	public Boolean jobDelete(JobBean bean) {
		Boolean flag=false;
		try{
			dao.delete(bean);
			flag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}
}
