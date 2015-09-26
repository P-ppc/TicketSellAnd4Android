package com.ppc.ticket4Android.activity;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.OrderBean;
import com.ppc.ticket4Android.bean.OrderStateBean;
import com.ppc.ticket4Android.bean.PhoneUserBean;
import com.ppc.ticket4Android.service.TicketService;
import com.ppc.ticket4Android.service.impl.TicketServiceImpl;
import com.ppc.ticket4Android.util.MyUtils;

public class UnpaidOrderListActivity extends Activity{

	ListView list;
	ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String,Object>>();
	SimpleAdapter listItemAdapter ;
	TicketService service= new TicketServiceImpl();
	OrderBean order= new OrderBean();
	Thread thread;
	TextView topText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.unpaid_order);
		init();
		SharedPreferences sp= getSharedPreferences("PhoneUser",MODE_PRIVATE);
		PhoneUserBean user= new PhoneUserBean();
		user.setUserName(sp.getString("userName", null));
		OrderStateBean state=new OrderStateBean();
		state.setStateNum(0);
		order.setUser(user);
		order.setState(state);
		Thread th= new Thread(thread);
		th.start();
		try {
			th.join();
			listItemAdapter.notifyDataSetChanged();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void init(){
		topText=(TextView) findViewById(R.id.top_textview);
		topText.setText("未完成订单");
		list = (ListView) findViewById(R.id.unpaid_order_listview);
		listItemAdapter= new SimpleAdapter(this,listItem,//数据源 
	            R.layout.order_items,//ListItem的XML实现
	            //动态数组与ImageItem对应的子项        
	            new String[] {"orderNum","passengerName","state"}, 
	            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
	            new int[] {R.id.order_items_orderNum,R.id.order_items_passengerName,R.id.order_items_state}
	        );
	       
	        //添加并且显示
	        list.setAdapter(listItemAdapter);
	        
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					   Intent intent= new Intent();
					   Bundle extras= new Bundle();
					   HashMap<String,Object> map =listItem.get(arg2);
					   for(String key : map.keySet()){
						   extras.putString(key, map.get(key).toString());
					   }
					   intent.putExtras(extras);
					   intent.setClass(UnpaidOrderListActivity.this,UnpaidOrderDetialActivity.class);
					   startActivity(intent);
					   finish();
					
				}
			});
	        
	        thread= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					listItem.clear();
	                 ArrayList<HashMap<String, Object>> orders= service.getUserEffectiveOrder(order);
	                 ArrayList<HashMap<String, Object>> newOrders= new ArrayList<HashMap<String,Object>>();
	                 
	                 for(int i=0;i<orders.size();i++){
	                	 HashMap<String,Object> map = new HashMap<String, Object>();
	                	 for(String key :orders.get(i).keySet()){
	                		 if(key!="state" && key!="ticket" && key!="uesr" &&key!="worker"){
								 map.put(key, orders.get(i).get(key).toString());
								}
	                		 else{
	                			 HashMap<String, Object> object= (HashMap<String, Object>) orders.get(i).get(key);
	                			 if(MyUtils.isNotNull(object)){
	                				 for(String newKey : object.keySet()){
	                					 map.put(newKey, object.get(newKey).toString());
	                				 }
	                			 }
	                		 }
	                	 }
	                	 newOrders.add(map);
	                 }
	                 listItem.addAll(newOrders);
				} catch (Exception e) {
					Message msg = new Message();
					msg.what = 1;
					mHandler.sendMessage(msg);
				}
                 
			}
		});    
	
	}
	Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case 1:
				Toast.makeText(UnpaidOrderListActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};
    
	
}
