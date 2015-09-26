package com.ppc.Ticket_sell.dao.impl;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.WorkerBean;
import com.ppc.Ticket_sell.dao.BaseDao;
import com.ppc.Ticket_sell.dao.WorkerDao;

public class WorkerDaoImpl extends BaseDaoImpl implements WorkerDao {

	BaseDao dao=new BaseDaoImpl();
	
	@Override
	public ArrayList<WorkerBean> sqlQuery(int pageNum, int pageSize,
			WorkerBean bean) {
	    String hql="from WorkerBean where 1=1"+this.getHql(bean);
	    System.out.println("hql----"+hql);
	    ArrayList<WorkerBean> list=(ArrayList<WorkerBean>) dao.getQueryByPage(pageNum, pageSize, hql);
		return list;
	}

	@Override
	public Long sqlGetTotal(WorkerBean bean) {
		String hql="select count(*) from WorkerBean where 1=1"+this.getHql(bean);
		return dao.getTotal(hql);
	}

	@Override
	public WorkerBean sqlFindById(String id) {
		String hql="from WorkerBean where workerName='"+id+"'";
		ArrayList<WorkerBean> list=(ArrayList<WorkerBean>) dao.getQueryByPage(1, 10, hql);
		if(list.size()==1){
		    return list.get(0);
		}else
			return null;
	}
	

	public String getHql(WorkerBean bean){
		String hql="";
		if(bean.getWorkerName()!=null && bean.getWorkerName()!=""){
			hql+="and workerName='"+bean.getWorkerName()+"'";
		}
		if(bean.getPassword()!=null && bean.getPassword()!=""){
			hql+="and password='"+bean.getPassword()+"'";
		}
		if(bean.getName()!=null && bean.getName()!=""){
			hql+="and name='"+bean.getName()+"'";
		}
		if(bean.getTel()!=null && bean.getTel().toString()!=""){
			hql+="and tel='"+bean.getTel().toString()+"'";
		}
		if(bean.getJob()!=null && bean.getJob().getJobNum()!=null && bean.getJob().getJobNum().toString()!=""){
			hql+="and job.jobNum='"+bean.getJob().getJobNum()+"'";
		}
		if(bean.getJurisdiction()!=null && bean.getJurisdiction().getJurisdictionNum()!=null && bean.getJurisdiction().getJurisdictionNum().toString()!=""){
			hql+="and jurisdiction.jurisdictionNum='"+bean.getJurisdiction().getJurisdictionNum()+"'";
		}
		return hql;
	}

}
