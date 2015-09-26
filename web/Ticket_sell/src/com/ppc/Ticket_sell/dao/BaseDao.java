package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;

public interface BaseDao {

	public ArrayList<?> getQueryByPage(int pageNum,int pageSize,String hql);
	
	public void delete(Object object);
	
	public void savaOrUpdate(Object object);
	
	public Long getTotal(String hql);
	
	public ArrayList<?> getQueryAll(String hql);
	
	public String getSaveResultId(Object object);
	
	public Boolean updateBySql(String sql);
}
