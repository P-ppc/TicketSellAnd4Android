package com.ppc.ticket4Android.activity;

import android.app.Activity;
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

public class ResetPasswordActivity  extends Activity{
	EditText userName;
	EditText phoneNum;
	EditText newPassword;
	Button changePwdBtn;
	Thread changePwdTh;
	PhoneUserBean user= new PhoneUserBean();
	PhoneUserService service= new PhoneUserServiceImpl();
	Boolean flag= false;
	TextView topText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reset_password);
		init();
		changePwdBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                if(!TextUtils.isEmpty(userName.getText().toString()) && !TextUtils.isEmpty(phoneNum.getText().toString()) && 
                		!TextUtils.isEmpty(newPassword.getText().toString())){
                	user.setUserName(userName.getText().toString());
                	user.setTel(Long.parseLong(phoneNum.getText().toString()));
                	user.setPassword(newPassword.getText().toString());
                	Thread th= new Thread(changePwdTh);
                	try {
						th.start();
						th.join();
					} catch (Exception e) {
						// TODO: handle exception
					}
                	if(flag!=null && flag.equals(true)){
                		Toast.makeText(ResetPasswordActivity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                		finish();
                	}
                	if(flag!=null && flag.equals(false)){
                		Toast.makeText(ResetPasswordActivity.this,"修改信息错误！请重新输入！", Toast.LENGTH_SHORT).show();
                	}
                }
                else{
                	Toast.makeText(ResetPasswordActivity.this,"请输入信息！", Toast.LENGTH_SHORT).show();
                }
			}
		});
	}
	public void init(){
		userName=(EditText) findViewById(R.id.reset_password_name);
		phoneNum=(EditText) findViewById(R.id.reset_password_phoneNum);
		newPassword=(EditText) findViewById(R.id.reset_password_newPassword);
		changePwdBtn=(Button) findViewById(R.id.reset_password_btn);
		topText=(TextView) findViewById(R.id.top_textview);
		topText.setText("密码找回");
		changePwdTh= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try { 
					flag=service.passwordReset(user);
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
				Toast.makeText(ResetPasswordActivity.this,"服务器连接异常！", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};
}
