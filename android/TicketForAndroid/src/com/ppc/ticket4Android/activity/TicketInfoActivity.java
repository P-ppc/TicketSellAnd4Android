package com.ppc.ticket4Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.OrderBean;
import com.ppc.ticket4Android.bean.TicketBean;
import com.ppc.ticket4Android.service.TicketService;
import com.ppc.ticket4Android.service.impl.TicketServiceImpl;

public class TicketInfoActivity extends Activity
	{
	    TicketService service= new TicketServiceImpl();
	    ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String,Object>>();
	    ListView list;
	    SimpleAdapter listItemAdapter ;
	    OrderBean order;
	    TextView topText;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.ticket_info);
	        //绑定Layout里面的ListView
	       
	        init();
	     
	        Bundle bundle=this.getIntent().getExtras();
	        order= new OrderBean();
	        TicketBean ticket= new TicketBean();
	        ticket.setStart_station(bundle.getString("start_station"));
	        setTitle(ticket.getStart_station().toString());
	        ticket.setEnd_station(bundle.getString("end_station"));
	        order.setDate(bundle.getString("date"));
	        order.setTicket(ticket);
	        
	        Thread thread= new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
					 listItem.clear();
					 listItem.addAll(service.getResidueTicketsListItem(order));
					}catch(Exception ex){
						Message msg = new Message();
						msg.what = 1;
						mHandler.sendMessage(msg);
					}
				}
			});
	        Thread th= new Thread(thread);
	        th.start();
	        try{
	        	th.join();
	        	listItemAdapter.notifyDataSetChanged();
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	       

	        
	        //添加点击
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					//setTitle("点击第"+arg2+"个项目");
					//加载到预订Activity
					Intent intent= new Intent();
					intent.setClass(TicketInfoActivity.this,TicketDetailActivity.class);
					Bundle extras=new Bundle();
					TextView start_station=(TextView)arg1.findViewById(R.id.ticket_info_start_station);
					TextView end_station=(TextView)arg1.findViewById(R.id.ticket_info_end_station);
					TextView price=(TextView) arg1.findViewById(R.id.ticket_info_price);
					TextView time=(TextView) arg1.findViewById(R.id.ticket_info_time);
					TextView  motorcoach_number=(TextView) arg1.findViewById(R.id.ticket_info_motorcoach_number);
					TextView residueSeat=(TextView) arg1.findViewById(R.id.ticket_info_residueSeat);
				        
					extras.putString("start_station",start_station.getText().toString());
					extras.putString("end_station", end_station.getText().toString());
					extras.putString("price", price.getText().toString());
					extras.putString("time", time.getText().toString());
					extras.putString("ticketNum",listItem.get(arg2).get("ticketNum").toString());
					extras.putString("motorcoach_number", motorcoach_number.getText().toString());
					extras.putString("residueSeat", residueSeat.getText().toString());
					extras.putString("date", listItem.get(arg2).get("date").toString());
					intent.putExtras(extras);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
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
	    }
		
		public void init(){
			topText=(TextView) findViewById(R.id.top_textview);
			topText.setText("车票信息");
			list = (ListView) findViewById(R.id.ListView01);
			
			listItemAdapter= new SimpleAdapter(this,listItem,//数据源 
		            R.layout.list_items,//ListItem的XML实现
		            //动态数组与ImageItem对应的子项        
		            new String[] {"start_station","end_station","price","time","motorcoach_number","residueSeat"}, 
		            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
		            new int[] {R.id.ticket_info_start_station,R.id.ticket_info_end_station,R.id.ticket_info_price,R.id.ticket_info_time,R.id.ticket_info_motorcoach_number,R.id.ticket_info_residueSeat}
		        );
		       
		        //添加并且显示
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
					Toast.makeText(TicketInfoActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		};
	}
