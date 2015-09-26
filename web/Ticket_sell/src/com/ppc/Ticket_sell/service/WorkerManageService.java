package com.ppc.Ticket_sell.service;

import com.ppc.Ticket_sell.bean.WorkerBean;

public interface WorkerManageService  {

	public Boolean Login(WorkerBean bean);
	
	public Object getWorkerInfo(int pageNum,int pageSize,WorkerBean bean);
	
	public Boolean workerAddOrUpdate(WorkerBean bean);
	
	public Boolean workerDelete(WorkerBean bean);
	
	public WorkerBean getWorker(WorkerBean bean);
}
