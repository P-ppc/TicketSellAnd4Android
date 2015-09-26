package com.ppc.Ticket_sell.dao.impl;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.JobBean;
import com.ppc.Ticket_sell.dao.BaseDao;
import com.ppc.Ticket_sell.dao.JobDao;

public class JobDaoImpl extends BaseDaoImpl implements JobDao {

	BaseDao dao= new BaseDaoImpl();
	public String getHql(JobBean bean){
		String hql="";
		if(bean.getJobNum()!=null && bean.getJobNum().toString()!=""){
			hql+="and jobNum='"+bean.getJobNum().toString()+"'";
		}
		if(bean.getJobName()!=null && bean.getJobName()!=""){
			hql+="and jobName="+bean.getJobName();
		}
		return hql;
	}
	

	@Override
	public ArrayList<JobBean> sqlQuery(int pageNum, int pageSize, JobBean bean) {
		String hql="from JobBean where 1=1"+this.getHql(bean);
		ArrayList<JobBean> list=(ArrayList<JobBean>) dao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public Long sqlGetTotal(JobBean bean) {
		String hql="select count(*) from JobBean where 1=1"+this.getHql(bean);
		Long total=dao.getTotal(hql);
		return total;
	}
}
