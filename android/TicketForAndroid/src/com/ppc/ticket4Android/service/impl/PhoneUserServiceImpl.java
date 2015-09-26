package com.ppc.ticket4Android.service.impl;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HTTP;







import com.ppc.ticket4Android.bean.PhoneUserBean;
import com.ppc.ticket4Android.service.PhoneUserService;
import com.ppc.ticket4Android.util.Contstant;
import com.ppc.ticket4Android.util.HttpUtils;
import com.ppc.ticket4Android.util.MyUtils;

public class PhoneUserServiceImpl implements PhoneUserService {
	private String baseUri="http://192.168.253.1:8080/Ticket/Page/";
	@Override
	public Boolean login(PhoneUserBean bean) {
		Boolean flag=false;
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "query");
		if(MyUtils.isNotNull(bean.getUserName())){
			params.put("userName", bean.getUserName());
		}
		if(MyUtils.isNotNull(bean.getPassword())){
			params.put("password", bean.getPassword());
		}
		String uri=Contstant.BASE_URI+"phoneUserManage";
		String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			return null;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<Object> phoneUser;
		try{
			phoneUser=HttpUtils.json2ArrayListByRows(resultJson);
			if(phoneUser.size()==1){
				flag=true;
				//保存进SharedPreferences对象
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return flag;
	}
	@Override
	public Boolean register(PhoneUserBean bean) {
		
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "register");
		if(MyUtils.isNotNull(bean.getUserName())){
			params.put("userName", bean.getUserName());
		}
		if(MyUtils.isNotNull(bean.getPassword())){
			params.put("password", bean.getPassword());
		}
		if(MyUtils.isNotNull(bean.getTel())){
			params.put("tel",bean.getTel().toString());
		}
		if(MyUtils.isNotNull(bean.getEmail())){
			params.put("email",bean.getEmail().toString());
		}
		String uri=Contstant.BASE_URI+"phoneUserManage";
		String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			return null;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return null;
		}
		try {
			if(resultJson==""){
				return null;
			}
			if(resultJson.equals("true")){
				return true;
			}
		} catch (Exception e) {
			return null;
		}
		return false;
	}
	
	@Override
	public Boolean passwordReset(PhoneUserBean bean) {
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "resetPassword");
		if(MyUtils.isNotNull(bean.getUserName())){
			params.put("userName", bean.getUserName());
		}
		if(MyUtils.isNotNull(bean.getPassword())){
			params.put("password", bean.getPassword());
		}
		if(MyUtils.isNotNull(bean.getTel())){
			params.put("tel",bean.getTel().toString());
		}
		if(MyUtils.isNotNull(bean.getEmail())){
			params.put("email",bean.getEmail().toString());
		}
		String uri=Contstant.BASE_URI+"phoneUserManage";
		
		try {
			String resultJson=HttpUtils.submitPostData(params, HTTP.UTF_8, uri);
			if(resultJson==""){
				return null;
			}
			if(resultJson.equals("true")){
				return true;
			}
		} catch (Exception e) {
			return null;
		}
		return false;
		
	}

}
