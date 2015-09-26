package com.ppc.Ticket_sell.util;

public class MyUtils {
   
	public static Boolean isNotNull(Object object){
		try{
			if(object!=null && object.toString()!=""){
				return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
