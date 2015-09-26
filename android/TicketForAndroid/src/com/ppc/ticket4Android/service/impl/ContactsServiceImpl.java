package com.ppc.ticket4Android.service.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.ppc.ticket4Android.bean.ContactsBean;
import com.ppc.ticket4Android.bean.PhoneUserBean;
import com.ppc.ticket4Android.service.ContactsService;
import com.ppc.ticket4Android.util.Contstant;
import com.ppc.ticket4Android.util.HttpUtils;
import com.ppc.ticket4Android.util.MyUtils;

public class ContactsServiceImpl implements ContactsService {
	
	
	@Override
	public ArrayList<ContactsBean> getContacts(ContactsBean bean) {
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getContactsListItem(
			ContactsBean bean) {
		ArrayList<HashMap<String, Object>>	list= new ArrayList<HashMap<String,Object>>();
	    HashMap<String, String> params= new HashMap<String, String>();
	    params.put("function", "query");
	    if(MyUtils.isNotNull(bean.getContactId())){
	    	params.put("contactId", bean.getContactId());
	    }
	    if(MyUtils.isNotNull(bean.getContactName())){
	    	params.put("contactName", bean.getContactName());
	    }
	    if(MyUtils.isNotNull(bean.getContactNo())){
	    	params.put("contactNo", bean.getContactNo().toString());
	    }
	    if(MyUtils.isNotNull(bean.getUser().getUserName())){
	    	params.put("userName", bean.getUser().getUserName());
	    }
	    if(MyUtils.isNotNull(bean.getUser().getPassword())){
	    	params.put("password",bean.getUser().getPassword());
	    }
	    String uri=Contstant.BASE_URI+"contactsManage";
	    String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params,HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e1) {
			e1.printStackTrace();
			return null;
		} catch (SocketTimeoutException e1) {
			e1.printStackTrace();
			return null;
		}
	    try {
			ArrayList<Object> contacts=HttpUtils.json2ArrayListByRows(resultJson.replaceAll(" ", ""));
			for(int i=0;i<contacts.size();i++){
				 HashMap<String, Object> map= new HashMap<String, Object>();
				 map=(HashMap<String, Object>) contacts.get(i);
				 list.add(map);
			}
			return list;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean ContactsAddOrUpdate(ContactsBean bean) {
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "addOrUpdate");
	    if(MyUtils.isNotNull(bean.getContactId())){
	    	params.put("contactId", bean.getContactId());
	    }
	    if(MyUtils.isNotNull(bean.getContactName())){
	    	params.put("contactName", bean.getContactName());
	    }
	    if(MyUtils.isNotNull(bean.getContactNo())){
	    	params.put("contactNo", bean.getContactNo().toString());
	    }
	    if(MyUtils.isNotNull(bean.getUser().getUserName())){
	    	params.put("userName", bean.getUser().getUserName());
	    }
	    if(MyUtils.isNotNull(bean.getUser().getPassword())){
	    	params.put("password",bean.getUser().getPassword());
	    }
	    String uri=Contstant.BASE_URI+"contactsManage";
	    String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params,HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			return null;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return null;
		}
	    if(resultJson.equals("true")){
	    	return true;
	    }
		return false;
	}

	@Override
	public Boolean ContactsDelete(ContactsBean bean) {
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("function", "delete");
	    if(MyUtils.isNotNull(bean.getContactId())){
	    	params.put("contactId", bean.getContactId());
	    }
	    if(MyUtils.isNotNull(bean.getContactName())){
	    	params.put("contactName", bean.getContactName());
	    }
	    if(MyUtils.isNotNull(bean.getContactNo())){
	    	params.put("contactNo", bean.getContactNo().toString());
	    }
	    if(MyUtils.isNotNull(bean.getUser().getUserName())){
	    	params.put("userName", bean.getUser().getUserName());
	    }
	    if(MyUtils.isNotNull(bean.getUser().getPassword())){
	    	params.put("password",bean.getUser().getPassword());
	    }
	    String uri=Contstant.BASE_URI+"contactsManage";
	    String resultJson;
		try {
			resultJson = HttpUtils.submitPostData(params,HTTP.UTF_8, uri);
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			return null;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return null;
		}
	    if(resultJson.equals("true")){
	    	return true;
	    }
		return false;
	}

}
