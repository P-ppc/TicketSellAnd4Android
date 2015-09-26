package com.ppc.ticket4Android.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ppc.ticket.R;
import com.ppc.ticket4Android.bean.ContactsBean;
import com.ppc.ticket4Android.bean.PhoneUserBean;
import com.ppc.ticket4Android.service.ContactsService;
import com.ppc.ticket4Android.service.impl.ContactsServiceImpl;
import com.ppc.ticket4Android.util.MyUtils;

public class ContactEditActivity extends Activity{

	private EditText contactName;
	private EditText contactId;
	private Button   confirm;
	private Button delete;
	String contactNo;
	ContactsService service= new ContactsServiceImpl();
	Thread addOrUpTh;
	Thread deleteTh;
	Boolean flag=false;
	ContactsBean contact= new ContactsBean();
	PhoneUserBean user= new PhoneUserBean();
	TextView topText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_eidt);
		Bundle extras=this.getIntent().getExtras();
		
		init();
		
		if(extras!=null){
			contactName.setText(extras.getString("contact_name").toString());
			contactId.setText(extras.getString("contact_id").toString());
			contactNo=extras.getString("contactNo").toString();
		}
		confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences sp=getSharedPreferences("PhoneUser",MODE_PRIVATE);
		        user.setUserName(sp.getString("userName", null));
		        contact.setUser(user);
		        contact.setContactName(contactName.getText().toString());
                contact.setContactId(contactId.getText().toString());
                if(!TextUtils.isEmpty(contactNo)){
                   contact.setContactNo(Integer.parseInt(contactNo));
                 }
                if(  !TextUtils.isEmpty(contact.getContactName().trim())
                		&&!TextUtils.isEmpty(contact.getContactId().trim())){
                    Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
                    //通过Pattern获得Matcher  
                    Matcher idNumMatcher = idNumPattern.matcher(contact.getContactId());
                    if(idNumMatcher.matches()){
                    	Thread th= new Thread(addOrUpTh);
                        th.start();
                        try {
        					th.join();
        				} catch (Exception e) {
        					// TODO: handle exception
        				}
                        setResult(RESULT_OK);
                        Toast.makeText(ContactEditActivity.this, "操作成功！",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                    	Toast.makeText(ContactEditActivity.this, "身份证格式不正确！", Toast.LENGTH_SHORT).show();
                    }
                	 
                }else{
                	Toast.makeText(ContactEditActivity.this,"请输入完整信息！",Toast.LENGTH_SHORT).show();
                }
                
               
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferences sp=getSharedPreferences("PhoneUser",MODE_PRIVATE);
		        user.setUserName(sp.getString("userName", null));
		        contact.setUser(user);
		        contact.setContactName(contactName.getText().toString());
                contact.setContactId(contactId.getText().toString());
                if(MyUtils.isNotNull(contactNo)){
                   contact.setContactNo(Integer.parseInt(contactNo));
                   Thread th= new Thread(deleteTh);
                   th.start();
                   try {
					  th.join();
				    } catch (Exception e) {
				      }
                  setResult(RESULT_OK);
                  Toast.makeText(ContactEditActivity.this, "删除成功！",Toast.LENGTH_SHORT).show();
                  finish();
			     }else{
			    	 contactName.setText("");
			    	 contactId.setText("");
			     }
          }
		});
	}
	
	public void init(){
		contactName=(EditText) findViewById(R.id.contact_edit_contactName);
		contactId=(EditText) findViewById(R.id.contact_edit_contactId);
		confirm=(Button) findViewById(R.id.contact_edit_confirm);
		delete=(Button) findViewById(R.id.contact_edit_delete);
		topText=(TextView) findViewById(R.id.top_textview);
		topText.setText("联系人编辑");
		
		addOrUpTh= new Thread(new Runnable() {
			
			@Override
			public void run() {
				 flag=service.ContactsAddOrUpdate(contact);
			}
		});
		deleteTh=new Thread(new Runnable() {
			
			@Override
			public void run() {
				 flag=service.ContactsDelete(contact);
			}
		});
	} 
    
	
}
