package com.ppc.ticket4Android.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ppc.ticket4Android.bean.TicketBean;


public class HttpUtils
	{
	
	/*
     * Function  :   发送Post请求到服务器
     * Param     :   params请求体内容，encode编码格式
     * Author    : 
     */
    public static String submitPostData(Map<String, String> params, String encode,String URL_PATH) throws ConnectTimeoutException,SocketTimeoutException {
        
    	 List<NameValuePair> list = new ArrayList<NameValuePair>(); 
    	 if((params != null) && !params.isEmpty()) {
    		             for(Map.Entry<String, String> param : params.entrySet()) {
    		                  list.add(new BasicNameValuePair(param.getKey(), param.getValue()));
    		            }
    		        }
    	
        try {
        	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,HTTP.UTF_8); 
        	HttpPost httpPost = new HttpPost(URL_PATH);
        	httpPost.setEntity(entity); 
        	DefaultHttpClient client = new DefaultHttpClient();
        	//连接超时
        	client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
        	//请求超时
        	client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
        	HttpResponse httpResponse = client.execute(httpPost);                      //执行POST请求
            int response = httpResponse.getStatusLine().getStatusCode();           //获得服务器的响应码
            if(response == HttpStatus.SC_OK) {
            	 String resultData = EntityUtils.toString(httpResponse.getEntity());  
            	 return resultData;                   //处理服务器的响应结果
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
      
     public static ArrayList<Object> json2ArrayListByRows (String jsonStr) throws JsonParseException, JsonMappingException, IOException{
    	 ObjectMapper mapper= new ObjectMapper();
    	 Map map=mapper.readValue(jsonStr, Map.class);
    	 ArrayList<Object> object= (ArrayList<Object>) map.get("rows");
    	  
    	 return object;
    	 
     }
     public static ArrayList<Object> json2ArrayList(String jsonStr) throws JsonParseException, JsonMappingException, IOException{
    	 ObjectMapper mapper= new ObjectMapper();
    	 Map map=mapper.readValue(jsonStr, Map.class);
    	 ArrayList<Object> object=(ArrayList<Object>) map;
    	 return object;
     }
     
    	 
     
     

	}
