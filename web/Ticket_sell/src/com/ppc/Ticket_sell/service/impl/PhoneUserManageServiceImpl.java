package com.ppc.Ticket_sell.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ppc.Ticket_sell.bean.PhoneUserBean;
import com.ppc.Ticket_sell.dao.PhoneUserDao;
import com.ppc.Ticket_sell.dao.impl.PhoneUserDaoImpl;
import com.ppc.Ticket_sell.service.PhoneUserManageService;

public class PhoneUserManageServiceImpl implements PhoneUserManageService {

	PhoneUserDao dao= new PhoneUserDaoImpl();
	@Override
	public Object getPhoneUserInfo(PhoneUserBean bean) {
		ArrayList<PhoneUserBean> list= dao.sqlQuery(bean);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("rows", list);
		net.sf.json.JSONObject object =JSONObject.fromObject(map);
		System.out.println(object.toString());
		return object;
	}

	@Override
	public Boolean register(PhoneUserBean bean) {
         PhoneUserBean newBean = new PhoneUserBean();
         newBean.setUserName(bean.getUserName());
         ArrayList<PhoneUserBean> list=dao.sqlQuery(newBean);
         if(list.size()==0){
        	 try{
        		 dao.getSaveResultId(bean);
        		 return true;
        	 }catch(Exception ex){
        		 ex.printStackTrace();
        		 return false;
        	 }
         }
         return false;
	}

	@Override
	public Boolean PhoneUserDelete(PhoneUserBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean resetPassword(PhoneUserBean bean) {
		PhoneUserBean newBean = new PhoneUserBean();
        newBean.setUserName(bean.getUserName());
        newBean.setTel(bean.getTel());
        ArrayList<PhoneUserBean> list=dao.sqlQuery(newBean);
        if(list.size()==1){
        	try {
        		bean.setEmail(list.get(0).getEmail());
				dao.savaOrUpdate(bean);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
        }
		
		return false;
	}

}
