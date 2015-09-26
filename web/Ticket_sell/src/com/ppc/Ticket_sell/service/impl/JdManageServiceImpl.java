package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.JurisdictionBean;
import com.ppc.Ticket_sell.dao.JdDao;
import com.ppc.Ticket_sell.dao.impl.JdDaoImpl;
import com.ppc.Ticket_sell.service.JdManageService;

public class JdManageServiceImpl implements JdManageService {

	JdDao dao= new JdDaoImpl();
	@Override
	public Object getJdInfo(int pageNo, int pageSize, JurisdictionBean bean) {
		ArrayList<JurisdictionBean> list=dao.sqlQuery(pageNo, pageSize, bean);
		Long total=dao.sqlGetTotal(bean);
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);
		net.sf.json.JSONObject array=JSONObject.fromObject(map);
		return array;
	}
	@Override
	public Boolean jdAddOrUpdate(JurisdictionBean bean) {
		Boolean flag=dao.sqlAddOrUpdate(bean);
		return flag;
	}
	@Override
	public Boolean jdDelete(JurisdictionBean bean) {
	    Boolean flag=dao.sqlDelete(bean);
	    return flag;
	}
}
