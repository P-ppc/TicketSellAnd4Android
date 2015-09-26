package com.ppc.ticket4Android.activity;


import com.ppc.ticket.R;
import com.ppc.ticket4Android.util.MyUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TicketDetailActivity extends Activity
	{
	   private Button order_btn;
	   private TextView start_station;
	   private TextView end_station;
	   private TextView price;
	   private TextView time;
	   private TextView date;
	   private TextView motorcoach_number;
	   private TextView residueSeat;
	   Bundle extras;
	   TextView topText;
       @Override
       public void onCreate(Bundle savedInstanceState){
    	   super.onCreate(savedInstanceState);
    	   requestWindowFeature(Window.FEATURE_NO_TITLE);
    	   setContentView(R.layout.ticket_detail);
    	   
    	   init();
    	   
    	   extras = this.getIntent().getExtras();
    	   start_station.setText(extras.getString("start_station"));
    	   end_station.setText(extras.getString("end_station"));
    	   price.setText(extras.getString("price"));
    	   time.setText(extras.getString("time"));
    	   date.setText(extras.getString("date"));
    	   motorcoach_number.setText(extras.getString("motorcoach_number"));
    	   residueSeat.setText(extras.getString("residueSeat"));
    	   topText.setText(extras.getString("date").toString().trim());
    	   order_btn=(Button) findViewById(R.id.order_btn);
    	   
    	   order_btn.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
					{
					    SharedPreferences sp=getSharedPreferences("PhoneUser", MODE_PRIVATE);
					    String userName=sp.getString("userName", null);
					    if(MyUtils.isNotNull(userName)){
						  Intent intent= new Intent();
						  intent.putExtras(extras);
						  intent.setClass(TicketDetailActivity.this, ChooseCaontactActivity.class);
						  startActivity(intent);
						  overridePendingTransition(0, 0);
						  finish();
					    }else{
					    	  Toast.makeText(TicketDetailActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
					    	  Intent intent= new Intent();
							  intent.setClass(TicketDetailActivity.this, LoginActivity.class);
							  startActivity(intent);
							  overridePendingTransition(0, 0);
							  finish();
					    }
					    
					}
			});
		}
       
       public void init(){
    	   start_station=(TextView) findViewById(R.id.ticket_detail_start_station);
    	   end_station=(TextView) findViewById(R.id.ticket_detail_end_station);
    	   price=(TextView) findViewById(R.id.ticket_detail_price);
    	   date=(TextView) findViewById(R.id.ticket_detail_date);
    	   time=(TextView) findViewById(R.id.ticket_detail_time);
    	   motorcoach_number=(TextView) findViewById(R.id.ticket_detail_motorcoach_number);
    	   residueSeat=(TextView) findViewById(R.id.ticket_detail_residueSeat);
    	   topText=(TextView) findViewById(R.id.top_textview);
       }
       }
	
