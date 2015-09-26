package com.ppc.ticket4Android.activity;



import android.app.Activity;
import android.content.SharedPreferences;
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

public class RegisterActivity extends Activity {

	EditText register_name;
	EditText register_password;
	EditText register_email;
	EditText register_phoneNum;
	Button register_btn;
	Button register_reset;
	Thread registerTh;
	PhoneUserBean user= new PhoneUserBean();
	Boolean flag=false;
	PhoneUserService service= new PhoneUserServiceImpl();
	TextView topText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		init();
		
		
		register_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                if(!TextUtils.isEmpty(register_name.getText().toString())&& !TextUtils.isEmpty(register_password.getText().toString())
                		&&!TextUtils.isEmpty(register_email.getText().toString()) && 
                		!TextUtils.isEmpty(register_phoneNum.getText().toString())){
                	user.setUserName(register_name.getText().toString());
                	user.setPassword(register_password.getText().toString());
                	user.setEmail(register_email.getText().toString());
                	user.setTel(Long.parseLong(register_phoneNum.getText().toString()));
                	Thread th= new Thread(registerTh);
                	try {
						th.start();
						th.join();
					} catch (Exception e) {
						// TODO: handle exception
					}
                     if(flag!=null && flag.equals(true)){
                     	Toast.makeText(RegisterActivity.this, "注册成功！",Toast.LENGTH_SHORT).show();
                     	finish();
                    }
                     if(flag!=null && flag.equals(false)){
                    	Toast.makeText(RegisterActivity.this, "该用户已存在！请重新输入用户名！", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                	Toast.makeText(RegisterActivity.this, "请输入完整注册信息！",Toast.LENGTH_SHORT).show();
                }
               
			}
		});
		
		
		register_reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                 register_name.setText("");    
                 register_password.setText("");  
                 register_email.setText("");  
                 register_phoneNum.setText("");  
			}
		});
		
		
		
	}
	
	public void init(){
		register_name=(EditText) findViewById(R.id.register_name);
		register_password=(EditText) findViewById(R.id.register_password);
		register_email=(EditText) findViewById(R.id.register_email);
		register_phoneNum=(EditText) findViewById(R.id.register_phoneNum);
		register_btn=(Button) findViewById(R.id.register_btn);
		register_reset=(Button) findViewById(R.id.register_reset);
		topText=(TextView) findViewById(R.id.top_textview);
		topText.setText("注册");
		
		registerTh= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					flag=service.register(user);
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
				Toast.makeText(RegisterActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};
    
}
