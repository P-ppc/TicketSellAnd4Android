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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.ContactsBean;
import com.ppc.ticket4Android.bean.PhoneUserBean;
import com.ppc.ticket4Android.service.ContactsService;
import com.ppc.ticket4Android.service.impl.ContactsServiceImpl;


public class ContactsInfoActivity extends Activity
	{
	    private int REQUEST_CODE=1;
        private Button addContact;	
        private TextView contact_name;
        private TextView contact_id;
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        SimpleAdapter listItemAdapter;
        ListView list;
        Thread thread;
        ContactsService service= new ContactsServiceImpl();
        ContactsBean bean= new ContactsBean();
        TextView topText;
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.contacts_info);
			
             
			init();
	        
	        list.setOnItemClickListener(new OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3)
						{
							Intent intent= new Intent();
							intent.setClass(ContactsInfoActivity.this, ContactEditActivity.class);
							Bundle extras=new Bundle();
							contact_name=(TextView) arg1.findViewById(R.id.contacts_info_name);
							contact_id=(TextView) arg1.findViewById(R.id.contacts_info_id);
							String contactNo=listItem.get(arg2).get("contactNo").toString();
							extras.putString("contact_name",contact_name.getText().toString());
							extras.putString("contact_id", contact_id.getText().toString());
							extras.putString("contactNo",contactNo);
							extras.putInt("index",arg2);
							intent.putExtras(extras);
							startActivityForResult(intent, REQUEST_CODE);
							overridePendingTransition(0, 0);
						  
						    
						}
					
				});
	        
	        addContact.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent= new Intent();
					intent.setClass(ContactsInfoActivity.this, ContactEditActivity.class);
					startActivityForResult(intent, REQUEST_CODE);
					overridePendingTransition(0, 0);
				}
			});
	        
	        Thread th=new Thread(thread);
	        th.start();
	        try {
				th.join();
				listItemAdapter.notifyDataSetChanged();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
		public void init(){
			topText=(TextView) findViewById(R.id.top_textview);
			topText.setText("联系人");
			addContact=(Button) findViewById(R.id.contacts_info_addContact);
			list = (ListView) findViewById(R.id.contacts_listView);
			listItemAdapter = new SimpleAdapter(this,listItem,//数据源 
			            R.layout.contacts_items,//ListItem的XML实现
			            
			            new String[] {"contactName","contactId"}, 
			            
			            new int[] {R.id.contacts_info_name,R.id.contacts_info_id}
			        );
			list.setAdapter(listItemAdapter);
			PhoneUserBean user= new PhoneUserBean();
			SharedPreferences sp= getSharedPreferences("PhoneUser",MODE_PRIVATE);
			String userName=sp.getString("userName", null);
			String password=sp.getString("password", null);
			user.setUserName(userName);
			user.setPassword(password);
			bean.setUser(user);
			
			
			
			thread= new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					listItem.clear();
					ArrayList<HashMap<String,Object>> map=service.getContactsListItem(bean);
					if(map!=null){
                        listItem.addAll(map);						
					}else{
						Message msg = new Message();
						msg.what = 1;
						mHandler.sendMessage(msg);
					}
					
					
				}
			});
			
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
							listItemAdapter.notifyDataSetChanged();
				}catch(Exception ex){
				}
			}
			
		}
			
   }
		Handler mHandler = new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					Toast.makeText(ContactsInfoActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		};
		
}
