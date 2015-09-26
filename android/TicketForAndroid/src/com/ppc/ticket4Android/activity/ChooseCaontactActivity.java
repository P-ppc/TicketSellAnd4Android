package com.ppc.ticket4Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.ContactsBean;
import com.ppc.ticket4Android.bean.OrderBean;
import com.ppc.ticket4Android.bean.OrderStateBean;
import com.ppc.ticket4Android.bean.PhoneUserBean;
import com.ppc.ticket4Android.bean.TicketBean;
import com.ppc.ticket4Android.service.ContactsService;
import com.ppc.ticket4Android.service.TicketService;
import com.ppc.ticket4Android.service.impl.ContactsServiceImpl;
import com.ppc.ticket4Android.service.impl.TicketServiceImpl;
import com.ppc.ticket4Android.util.ChooseContactAdapter;
import com.ppc.ticket4Android.util.HttpUtils;
import com.ppc.ticket4Android.util.MyUtils;

public class ChooseCaontactActivity extends Activity
	{
	    private int REQUEST_CODE=1;
	    ContactsService service= new ContactsServiceImpl();
	    TicketService ts= new TicketServiceImpl();
		private Button choose_contact_butOk;
		Button  choose_contact_addContact;
		ChooseContactAdapter adapter;
		ListView list;
		ContactsBean bean = new ContactsBean();
		OrderBean order = new OrderBean();
		ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String, Object>>();
		Thread thread;
		Thread thread1;
		Thread thread2;
		Bundle extras;
		String date;
		Integer ticketNum;
		PhoneUserBean user;
		ArrayList<HashMap<String, Object>>	result= new ArrayList<HashMap<String,Object>>();
		TextView topText;
		ArrayList<HashMap<String, Object>> unpaidEffectiveOrder= new  ArrayList<HashMap<String,Object>>();
		
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.choose_contact);
			init();
			 
			extras= this.getIntent().getExtras();
			date=extras.getString("date");
			ticketNum=Integer.parseInt(extras.getString("ticketNum"));
            setTitle(ticketNum.toString()+date);
	      
	        
	        
	        
	        choose_contact_butOk.setOnClickListener(new OnClickListener()
				{
					
					
					@Override
					public void onClick(View v)
						{
						  
							HashMap<String,Boolean> states=(HashMap<String, Boolean>) adapter.getStates();
						    if(states!=null){
						    int checkedItemIndex=-1;
						    for(int i=0;i<states.size();i++){
						    	if(states.get(String.valueOf(i))!=null && states.get(String.valueOf(i))==true){
						    		checkedItemIndex=i;
						    	}
						    }
						    if(checkedItemIndex!=-1){
						    	HashMap<String, Object> map=(HashMap<String, Object>) adapter.getItem(checkedItemIndex);
						    	order.setDate(date);
						    	order.setPassengerName(map.get("contactName").toString());
						    	order.setPassengerId(map.get("contactId").toString());
						    	TicketBean ticket= new TicketBean();
						    	ticket.setTicketNum(ticketNum);
						    	order.setTicket(ticket);
						    	order.setUser(user);
						    	OrderStateBean state= new OrderStateBean();
						    	state.setStateNum(0);
						    	order.setState(state);
						    	Thread th2= new Thread(thread2);
						    	Thread th1= new Thread(thread1);
						    	try {
									th2.start();
									th2.join();
									if(unpaidEffectiveOrder.size()==0){
										try {
								    		th1.start();
											th1.join();
											Map<String,Object> resultOrder = new HashMap<String,Object>();
											if(result==null){
												Toast.makeText(ChooseCaontactActivity.this, "没有座位了，请选择其他车次！", Toast.LENGTH_SHORT).show();
											}
											else{
												resultOrder=result.get(0);
												extras= new Bundle();
												for(String key: resultOrder.keySet()){
													if(key!="state" && key!="ticket" && key!="uesr" &&key!="worker"){
													  extras.putString(key, resultOrder.get(key).toString());
													}
													else{
														HashMap<String, Object> object= (HashMap<String, Object>) resultOrder.get(key);
														if(MyUtils.isNotNull(object)){
														  for(String newkey: object.keySet()){
															extras.putString(newkey, object.get(newkey).toString());
														  }
														}
														
													}
												
												}
					                            Intent intent= new Intent();
					                            intent.putExtras(extras);
					                            intent.setClass(ChooseCaontactActivity.this,UnpaidOrderDetialActivity.class);
					                            startActivity(intent);
					                            finish();
											}
										
										} catch (Exception e) {
											// TODO: handle exception
										}
									}
									else{
										Toast.makeText(ChooseCaontactActivity.this,"你还有未支付的订单，请先支付！",Toast.LENGTH_SHORT).show();
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
						    	
						    }
						}
					}
				});
	        
           choose_contact_addContact.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent= new Intent();
					intent.setClass(ChooseCaontactActivity.this, ContactEditActivity.class);
					startActivityForResult(intent, REQUEST_CODE);
					overridePendingTransition(0, 0);
				}
			});
	        Thread th= new Thread(thread);
	        th.start();
	        try {
				th.join();
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		public void init(){
			 list = (ListView) findViewById(R.id.choose_contact_listview);
			 adapter=new ChooseContactAdapter(this, listItem);
			 list.setAdapter(adapter);
			 list.setVisibility(View.VISIBLE);
			 choose_contact_butOk=(Button) findViewById(R.id.choose_contact_btnOk);
			 choose_contact_addContact=(Button) findViewById(R.id.choose_contact_addContact);
			 topText=(TextView) findViewById(R.id.top_textview);
			 topText.setText("选择联系人");
			 list.setOnItemClickListener(new OnItemClickListener()
				{
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3)
						{
						   HashMap<String, Object> map=(HashMap<String, Object>) adapter.getItem(arg2);

				    	   Intent intent= new Intent();
						   intent.setClass(ChooseCaontactActivity.this, ContactEditActivity.class);
						   Bundle extras=new Bundle();
						
						   extras.putString("contact_name",map.get("contactName").toString());
						   extras.putString("contact_id", map.get("contactId").toString());
						   extras.putString("contactNo", map.get("contactNo").toString());
						   extras.putInt("index",arg2);
						   intent.putExtras(extras);
						   startActivityForResult(intent, REQUEST_CODE);
						   overridePendingTransition(0, 0);
						}
				});
			   thread= new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							listItem.clear();
							listItem.addAll(service.getContactsListItem(bean));
						} catch (Exception e) {
							Message msg = new Message();
							msg.what = 1;
							mHandler.sendMessage(msg);
						}
						
					}
				});
			   thread1=new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						result= ts.buyTicket(order);
					} catch (Exception e) {
						Message msg = new Message();
						msg.what = 1;
						mHandler.sendMessage(msg);
					}
				    
					
				}
			}); 
			    thread2= new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							unpaidEffectiveOrder=ts.getUserEffectiveOrder(order);
						} catch (Exception e) {
							Message msg = new Message();
							msg.what = 1;
							mHandler.sendMessage(msg);
						}
						
					}
				});
			   
			    user= new PhoneUserBean();
				SharedPreferences sp= getSharedPreferences("PhoneUser",MODE_PRIVATE);
				String userName=sp.getString("userName", null);
				String password=sp.getString("password", null);
				user.setUserName(userName);
				user.setPassword(password);
				bean.setUser(user);
		}
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if(requestCode==REQUEST_CODE){
				if(resultCode==RESULT_OK){
					
						Thread th=new Thread(thread);
				        th.start();
				        try {
							th.join();
							adapter.notifyDataSetChanged();
				
				}catch(Exception ex){
					
				}
		}
		
		
	}
   }
		Handler mHandler = new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					Toast.makeText(ChooseCaontactActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		};
}