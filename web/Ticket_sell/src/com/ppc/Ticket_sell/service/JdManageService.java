package com.ppc.Ticket_sell.service;

import com.ppc.Ticket_sell.bean.JurisdictionBean;

public interface JdManageService {

	public Object getJdInfo(int pageNo,int pageSize,JurisdictionBean bean);
	
	public Boolean jdAddOrUpdate(JurisdictionBean bean);
	
	public Boolean jdDelete(JurisdictionBean bean);
}
