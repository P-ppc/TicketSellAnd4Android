package com.ppc.Ticket_sell.service;

import com.ppc.Ticket_sell.bean.JobBean;

public interface JobManageService {

	public Object getJobInfo(int pageNum,int pageSize,JobBean bean);
	
	public Boolean jobAddOrUpdate(JobBean bean);
	
	public Boolean jobDelete(JobBean bean);
	
}
