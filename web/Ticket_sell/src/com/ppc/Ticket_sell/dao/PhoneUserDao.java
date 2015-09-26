package com.ppc.Ticket_sell.dao;

import java.util.ArrayList;

import com.ppc.Ticket_sell.bean.PhoneUserBean;

public interface PhoneUserDao extends BaseDao {
   
	//根据分页获取手机用户信息
	public ArrayList<PhoneUserBean> sqlQueryByPage(int pageNum,int pageSize,PhoneUserBean bean);
	//获取total
	public Long sqlGetTotal(PhoneUserBean bean);
	//不分页获取手机用户信息
	public ArrayList<PhoneUserBean> sqlQuery(PhoneUserBean bean);
	
	
	
}
