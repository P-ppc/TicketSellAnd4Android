package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.JurisdictionBean;

public interface JdDao extends BaseDao {

	public ArrayList<JurisdictionBean> sqlQuery(int pageNum,int pageSize,JurisdictionBean bean);
	
	public Long sqlGetTotal(JurisdictionBean bean);
	
	public Boolean sqlAddOrUpdate(JurisdictionBean bean);
	
	public Boolean sqlDelete(JurisdictionBean bean);
}
