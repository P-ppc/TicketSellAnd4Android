package com.ppc.ticket4Android.activity;

import com.ppc.ticket.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class LoadActivity extends Activity {
	private static final int LOAD_DISPLAY_TIME = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.RGBA_8888);  
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);  
	    setContentView(R.layout.loading); 
	    new Handler().postDelayed(new Runnable() {  
            public void run() {  
                Intent mainIntent = new Intent(LoadActivity.this, MainActivity.class);  
                LoadActivity.this.startActivity(mainIntent); 
                overridePendingTransition(0, 0);
                LoadActivity.this.finish();  
            }  
        }, LOAD_DISPLAY_TIME);   
    }  
	}
	
	
