package com.ppc.ticket4Android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.OrderBean;
import com.ppc.ticket4Android.service.TicketService;
import com.ppc.ticket4Android.service.impl.TicketServiceImpl;
 
public class UnpaidOrderDetialActivity extends Activity
	{
	       TextView start_station;
	       TextView end_staion;
	       TextView moNum;
	       TextView date;
	       TextView time;
	       TextView seatNo;
	       TextView price;
	       TextView passengerName;
	       Button payBtn;
	       Button cancelBtn;
	       TicketService service= new TicketServiceImpl();
	       OrderBean order= new OrderBean();
	       Thread payTh;
	       Thread cancelTh;
	       Boolean flag=false;
	       Bundle extras;
	       TextView topText;
	       
		 @Override
	       public void onCreate(Bundle savedInstanceState){
	    	   super.onCreate(savedInstanceState);
	    	   requestWindowFeature(Window.FEATURE_NO_TITLE);
	    	   setContentView(R.layout.unpaid_order_detial);
	    	   extras= this.getIntent().getExtras();
	    	   init();
	    	   start_station.setText(extras.getString("start_station").trim());
	    	   end_staion.setText(extras.getString("end_station").trim());
	    	   moNum.setText(extras.getString("motorcoach_number").trim());
	    	   date.setText(extras.getString("date").trim());
	    	   time.setText(extras.getString("time").trim());
	    	   seatNo.setText(extras.getString("seatNo").trim());
	    	   price.setText(extras.getString("price").trim());
	    	   passengerName.setText(extras.getString("passengerName").trim());
	    	   topText.setText(extras.getString("orderNum").toString().trim());
	    	   payBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
                     	//支付宝？	
					flag=false;
					order.setOrderNum(Integer.parseInt(extras.getString("orderNum").trim()));
                	Thread th=new Thread(payTh);
                	try {
						th.start();
						th.join();
						if(flag.equals(true)){
							Toast.makeText(UnpaidOrderDetialActivity.this, "支付成功！",Toast.LENGTH_SHORT).show();
	                		Intent intent= new Intent();
	                		intent.setClass(UnpaidOrderDetialActivity.this,PaidOrderListActivity.class);
	                		startActivity(intent);
	                		overridePendingTransition(0, 0);
	                		finish();
	                	}
					} catch (Exception e) {
						e.printStackTrace();
					}
                	
				}
			});
	    	   cancelBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
                    	order.setOrderNum(Integer.parseInt(extras.getString("orderNum").trim()));
                    	flag=false;
                    	Thread th=new Thread(cancelTh);
                    	try {
							th.start();
							th.join();
							if(flag.equals(true)){
								Toast.makeText(UnpaidOrderDetialActivity.this, "取消成功！",Toast.LENGTH_SHORT).show();
	                    		Intent intent= new Intent();
	                    		intent.setClass(UnpaidOrderDetialActivity.this,UnpaidOrderListActivity.class);
	                    		startActivity(intent);
	                    		overridePendingTransition(0, 0);
	                    		finish();
	                    	}
						} catch (Exception e) {
							e.printStackTrace();
						}
                    	
				}
			});
	    	   
		 }
		 
		 public void init(){
			 start_station=(TextView) findViewById(R.id.order_ticket_start_station);
			 end_staion=(TextView) findViewById(R.id.order_ticket_end_station);
			 moNum=(TextView) findViewById(R.id.order_ticket_moNum);
			 date=(TextView) findViewById(R.id.order_ticket_date);
			 time=(TextView) findViewById(R.id.order_ticket_time);
			 seatNo=(TextView) findViewById(R.id.order_ticket_seatNo);
			 price=(TextView) findViewById(R.id.order_ticket_price);
			 passengerName=(TextView) findViewById(R.id.order_ticket_passengerName);
			 payBtn=(Button) findViewById(R.id.unpaid_order_detial_payBtn);
			 cancelBtn=(Button) findViewById(R.id.unpaid_order_detial_cancelBtn);
			 topText=(TextView) findViewById(R.id.top_textview);
			 
			 payTh= new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						flag=service.payOrder(order);
						if(flag==null){
							Message msg = new Message();
							msg.what = 1;
							mHandler.sendMessage(msg);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			});
			 cancelTh= new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						flag=service.cancelOrder(order);
						if(flag==null){
							Message msg = new Message();
							msg.what = 1;
							mHandler.sendMessage(msg);
						}
					}catch(Exception e){
						
					}
				}
			});
		 }
		 Handler mHandler = new Handler(){
				public void handleMessage(Message msg){
					switch (msg.what) {
					case 1:
						Toast.makeText(UnpaidOrderDetialActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
					}
				}
			};
	}
