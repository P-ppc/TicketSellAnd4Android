package com.ppc.ticket4Android.service;

import com.ppc.ticket4Android.bean.PhoneUserBean;

public interface PhoneUserService {
    //登录方法 
	public Boolean login(PhoneUserBean bean);
	
	//注册
	public Boolean register(PhoneUserBean bean);
	
	//修改
	public Boolean passwordReset(PhoneUserBean bean);
}
