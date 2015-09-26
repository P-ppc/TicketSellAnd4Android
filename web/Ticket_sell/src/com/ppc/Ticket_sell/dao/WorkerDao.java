package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;
import com.ppc.Ticket_sell.bean.WorkerBean;

public interface WorkerDao extends BaseDao {

	public  ArrayList<WorkerBean> sqlQuery(int pageNum,int pageSize,WorkerBean bean);
	
	public  Long sqlGetTotal(WorkerBean bean);
	
	public  WorkerBean sqlFindById(String id);
}
