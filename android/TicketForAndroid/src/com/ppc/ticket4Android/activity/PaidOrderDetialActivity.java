package com.ppc.ticket4Android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ppc.ticket.R;

public class PaidOrderDetialActivity  extends Activity{
    
	TextView paid_ticket_detial_start_station;
	TextView paid_ticket_detial_end_station;
	TextView paid_ticket_detial_moNum;
	TextView paid_ticket_detial_date;
	TextView paid_ticket_detial_time;
	TextView paid_ticket_detial_seatNo;
	TextView paid_ticket_detial_price;
	TextView paid_ticket_detial_passengerName;
	Button paid_ticket_datial_confirm;
	TextView topText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.paid_order_detial);
		
		init();
		Bundle extras= this.getIntent().getExtras();
		paid_ticket_detial_start_station.setText(extras.getString("start_station").trim());
		paid_ticket_detial_end_station.setText(extras.getString("end_station").trim());
		paid_ticket_detial_moNum.setText(extras.getString("motorcoach_number").trim());
		paid_ticket_detial_date.setText(extras.getString("date").trim());
		paid_ticket_detial_time.setText(extras.getString("time").trim());
		paid_ticket_detial_seatNo.setText(extras.getString("seatNo").trim());
		paid_ticket_detial_price.setText(extras.getString("price").trim());
		paid_ticket_detial_passengerName.setText(extras.getString("passengerName").trim());
		topText.setText(extras.getString("orderNum").toString().trim());
		
		paid_ticket_datial_confirm.setOnClickListener(new OnClickListener() {
	    
			@Override
			public void onClick(View v) {
                  finish(); 				
			}
		});
	}
    
	public void init(){
		paid_ticket_detial_start_station=(TextView) findViewById(R.id.paid_ticket_detial_start_station);
		paid_ticket_detial_end_station=(TextView) findViewById(R.id.paid_ticket_detial_end_station);
		paid_ticket_detial_moNum=(TextView) findViewById(R.id.paid_ticket_detial_moNum);
		paid_ticket_detial_date=(TextView) findViewById(R.id.paid_ticket_detial_date);
		paid_ticket_detial_time=(TextView) findViewById(R.id.paid_ticket_detial_time);
		paid_ticket_detial_seatNo=(TextView) findViewById(R.id.paid_ticket_detial_seatNo);
		paid_ticket_detial_price=(TextView) findViewById(R.id.paid_ticket_detial_price);
		paid_ticket_detial_passengerName=(TextView) findViewById(R.id.paid_ticket_detial_passengerName);
		paid_ticket_datial_confirm=(Button) findViewById(R.id.paid_order_detial_confirm);
		topText=(TextView) findViewById(R.id.top_textview);
	}
}
