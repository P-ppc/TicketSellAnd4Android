package com.ppc.ticket4Android.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.PhoneUserBean;
import com.ppc.ticket4Android.service.PhoneUserService;
import com.ppc.ticket4Android.service.impl.PhoneUserServiceImpl;
import com.ppc.ticket4Android.util.MyUtils;

public class LoginActivity extends Activity
	{
	   EditText login_name;
	   EditText login_password;
	   Button login_btn;
	   PhoneUserBean phoneUser=new PhoneUserBean();
	   PhoneUserService service = new PhoneUserServiceImpl();
	   TextView login_regiset;
	   TextView login_resetPassword;
	   Boolean flag=false;
	   Thread thread;
	   String target;
	   TextView topText;
	   
       @Override
       protected void onCreate(Bundle savedInstanceState){
    	   super.onCreate(savedInstanceState);
    	   requestWindowFeature(Window.FEATURE_NO_TITLE);
    	   setContentView(R.layout.login);
    	   init();
    	   Bundle extras=this.getIntent().getExtras();
    	   if(MyUtils.isNotNull(extras)){
    	      if(MyUtils.isNotNull(extras.get("target"))){
    		     target=extras.getString("target");
    	      }
    	   }
    	   setTitle(flag.toString());
    	   
    	   login_regiset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                  Intent intent= new Intent();
                  intent.setClass(LoginActivity.this,RegisterActivity.class);
                  startActivity(intent);
			}
		});
    	   login_resetPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  Intent intent= new Intent();
                  intent.setClass(LoginActivity.this,ResetPasswordActivity.class);
                  startActivity(intent);				
			}
		});
    	   
    	   login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(!TextUtils.isEmpty(login_name.getText().toString())&&
						!TextUtils.isEmpty(login_password.getText().toString())){	 
				  phoneUser.setUserName(login_name.getText().toString().trim());
			      phoneUser.setPassword(login_password.getText().toString().trim());
			      Thread th= new Thread(thread);
				  try {
				    th.start();
				    th.join();
				   } catch (Exception e) {
					  e.printStackTrace();
					}
				  if(flag!=null && flag==true){
						SharedPreferences sp=getSharedPreferences("PhoneUser",MODE_PRIVATE);
						Editor editor=sp.edit();
						editor.clear();
						editor.putString("userName", phoneUser.getUserName().trim());
						editor.putString("password", phoneUser.getPassword().trim());
						editor.commit();
						if(MyUtils.isNotNull(target)){
							if(target.equals("contactsInfo")){
								Intent intent= new Intent();
								intent.setClass(LoginActivity.this,ContactsInfoActivity.class);
								startActivity(intent);
								overridePendingTransition(0, 0);
								finish();
								}
							}else{
									Toast.makeText(LoginActivity.this,"登录成功！", Toast.LENGTH_SHORT).show();
									finish();
								}
						}
				   if(flag!=null && flag==false){
							Toast.makeText(LoginActivity.this,"用户名或密码错误！", Toast.LENGTH_SHORT).show();
						}
			   }else{
				   Toast.makeText(LoginActivity.this, "请输入完整登录信息！", Toast.LENGTH_SHORT).show();
			   }
			}
			
		});
       }
       
       public  void init(){
    	   login_name=(EditText) findViewById(R.id.login_name);
    	   login_password=(EditText) findViewById(R.id.login_password);
    	   login_btn=(Button) findViewById(R.id.login_btn);
    	   login_regiset=(TextView) findViewById(R.id.login_register);
    	   login_resetPassword=(TextView) findViewById(R.id.login_resetPassword);
    	   topText=(TextView) findViewById(R.id.top_textview);
		   topText.setText("登录");
    	   
    	   thread= new Thread(new Runnable() {
  		     @Override
  			 public void run() {
  		    	   try {
  		    		  flag=service.login(phoneUser);
  		    		  if(flag==null){
  		    			Message msg = new Message();
						msg.what = 1;
						mHandler.sendMessage(msg);
  		    		  } 
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
   				Toast.makeText(LoginActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
   				break;

   			default:
   				break;
   			}
   		}
   	};
	}
