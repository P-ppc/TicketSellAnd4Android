package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.JobBean;

public interface JobDao extends BaseDao {

	public ArrayList<JobBean> sqlQuery(int pageNum,int pageSize,JobBean bean);
	
	public Long sqlGetTotal(JobBean bean);
	
	
}
