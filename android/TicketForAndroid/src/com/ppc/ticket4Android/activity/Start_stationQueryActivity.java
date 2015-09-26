package com.ppc.ticket4Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.TicketBean;
import com.ppc.ticket4Android.service.TicketService;
import com.ppc.ticket4Android.service.impl.TicketServiceImpl;

public class Start_stationQueryActivity extends Activity
	{
        TicketService service=new TicketServiceImpl();
        List<HashMap<String, Object>> listItem= new ArrayList<HashMap<String,Object>>();
        SimpleAdapter listItemAdapter;
		ListView list;
		EditText start_station;
		Button start_station_queryBtn;
		TicketBean bean;
		Thread thread;
		TextView topText;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.start_station_query);
	        
	        
	        init();

	        thread= new Thread(new Runnable() {
				
				@Override
				public void run() {
                      try{
						listItem.clear();
						listItem.addAll(service.getTicketsListItem(bean));
					}catch(Exception ex){
						Message msg = new Message();
						msg.what = 1;
						mHandler.sendMessage(msg);
					}					
				}
			});
	    	Thread t=new Thread(thread);
			t.start();
			try {
				t.join();
				listItemAdapter.notifyDataSetChanged();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        //添加点击
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					TextView tv=(TextView) (arg1.findViewById(R.id.station_query));
				  //setTitle(tv.getText());
					
					Intent in= new Intent();
					Bundle bundle= new Bundle();
					bundle.putString("start_station", tv.getText().toString());
					in.putExtras(bundle);
					setResult(RESULT_OK,in);
					finish();
				}
			});
	        
//	      //添加长按点击
//	        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
//				
//				@Override
//				public void onCreateContextMenu(ContextMenu menu, View v,
//												ContextMenuInfo menuInfo) {
//					menu.setHeaderTitle("长按菜单-ContextMenu");   
//					menu.add(0, 0, 0, "弹出长按菜单0");
//					menu.add(0, 1, 0, "弹出长按菜单1");   
//				}
//			}); 
//	        
	        start_station_queryBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String start_query=start_station.getText().toString();
					bean.setStart_station(start_query);
					Thread t=new Thread(thread);
					t.start();
					try {
						t.join();
						listItemAdapter.notifyDataSetChanged();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
	    }
		
		public void init(){
			list=(ListView) findViewById(R.id.start_station_listview);
			start_station=(EditText) findViewById(R.id.start_station_query_edit);
			start_station_queryBtn=(Button) findViewById(R.id.start_station_query_button);
			topText=(TextView) findViewById(R.id.top_textview);
			topText.setText("起点站");
			bean= new TicketBean();
			
			listItemAdapter=new SimpleAdapter(this,
					listItem,
					R.layout.staion_items,
				    new String[]{"start_station"},
				    new int[]{R.id.station_query});
			
			list.setAdapter(listItemAdapter);
		}
//		//长按菜单响应函数
//		@Override
//		public boolean onContextItemSelected(MenuItem item) {
//			setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目"); 
//			return super.onContextItemSelected(item);
//		}
		Handler mHandler = new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					Toast.makeText(Start_stationQueryActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		};
	        
	}
		  
