package com.ppc.ticket4Android.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.ppc.calendar_library.KCalendar;
import com.ppc.calendar_library.KCalendar.OnCalendarClickListener;
import com.ppc.calendar_library.KCalendar.OnCalendarDateChangedListener;
import com.ppc.ticket.R;
import com.ppc.ticket4Android.util.MyUtils;

public class MainActivity extends Activity
	{
		private String date = null;  
	    private	 LinearLayout layout;
	    private  TextView dateText;
	    private  ImageView topMenuButton;
	    private  SlidingMenu slidingMenu;
	    private  Button queryBtn;
	    private LinearLayout start_staion_layout;
	    private LinearLayout end_station_layout;
	    private LinearLayout contacts_slidingMeun_layout;
	    private LinearLayout finish;
	    private TextView main_start_station;
	    private TextView main_end_station;
	    private LinearLayout unpaid_order_menu;
	    private LinearLayout paid_order_menu;
	    private LinearLayout menu_account_change;
	    private TextView main_topText;
	    
	    int REQUEST_CODE=1;
	    
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
			setContentView(R.layout.activity_main);
			
			init();
			//top菜单按钮监控
			topMenuButton.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
						{
							slidingMenu.toggle(true);
							
						}
				});
			//查询按钮监控
			queryBtn.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
						{
							Intent intent= new Intent();
							intent.setClass(MainActivity.this,TicketInfoActivity.class);
							Bundle bundle= new Bundle();
							bundle.putString("start_station", main_start_station.getText().toString());
							bundle.putString("end_station", main_end_station.getText().toString());
							bundle.putString("date",dateText.getText().toString());
							intent.putExtras(bundle);
							startActivity(intent);
							//取消切换activity时的动画效果
							overridePendingTransition(0,0); 
						}
				});
			
			//点击--日历
			layout.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					new PopupWindows(MainActivity.this, layout);
				}
			});
			//出发站layout监控
			start_staion_layout.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
						{
							Intent intent= new Intent();
							intent.setClass(MainActivity.this,Start_stationQueryActivity.class);
							startActivityForResult(intent,REQUEST_CODE);
							//取消切换activity时的动画效果
							overridePendingTransition(0,0); 
							
						}
				});
			//终点站layout监控
			end_station_layout.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
						{
							Intent intent=new Intent();
							intent.setClass(MainActivity.this, End_stationQueryActivity.class);
							startActivityForResult(intent, REQUEST_CODE);
							overridePendingTransition(0, 0);							
						}
				});
			//要在slidingmenu加载后加载
			contacts_slidingMeun_layout= (LinearLayout) findViewById(R.id.contactsslidingmenu);
			contacts_slidingMeun_layout.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
						{
						    SharedPreferences sp=getSharedPreferences("PhoneUser", MODE_PRIVATE);
						    String userName=sp.getString("userName", null);
						    if(MyUtils.isNotNull(userName)){
							   Intent intent=new Intent();
							   intent.setClass(MainActivity.this, ContactsInfoActivity.class);
							   startActivity(intent);
							   overridePendingTransition(0, 0);
							}else{
								  Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
						    	  Intent intent= new Intent();
								  intent.setClass(MainActivity.this, LoginActivity.class);
								  Bundle extras= new Bundle();
								   extras.putString("target","contactsInfo");
								   intent.putExtras(extras);
								  startActivity(intent);
								  overridePendingTransition(0, 0);
							}	
							
						}
				});
			unpaid_order_menu=(LinearLayout) findViewById(R.id.unpaid_order_menu);
			unpaid_order_menu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					SharedPreferences sp=getSharedPreferences("PhoneUser", MODE_PRIVATE);
				    String userName=sp.getString("userName", null);
				    if(MyUtils.isNotNull(userName)){
					   Intent intent=new Intent();
					   intent.setClass(MainActivity.this, UnpaidOrderListActivity.class);
					   startActivity(intent);
					   overridePendingTransition(0, 0);
					}else{
						  Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
				    	  Intent intent= new Intent();
						  intent.setClass(MainActivity.this, LoginActivity.class);
						  Bundle extras= new Bundle();
						   extras.putString("target","contactsInfo");
						   intent.putExtras(extras);
						  startActivity(intent);
						  overridePendingTransition(0, 0);
					}	
					
				}
			});
			paid_order_menu=(LinearLayout) findViewById(R.id.menu_paid_order);
			paid_order_menu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					SharedPreferences sp=getSharedPreferences("PhoneUser", MODE_PRIVATE);
				    String userName=sp.getString("userName", null);
				    if(MyUtils.isNotNull(userName)){
					   Intent intent=new Intent();
					   intent.setClass(MainActivity.this, PaidOrderListActivity.class);
					   startActivity(intent);
					   overridePendingTransition(0, 0);
					}else{
						  Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
				    	  Intent intent= new Intent();
						  intent.setClass(MainActivity.this, LoginActivity.class);
						  Bundle extras= new Bundle();
						   extras.putString("target","contactsInfo");
						   intent.putExtras(extras);
						  startActivity(intent);
						  overridePendingTransition(0, 0);
					}					
				}
			});
			menu_account_change=(LinearLayout) findViewById(R.id.menu_account_cahange);
			menu_account_change.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
                    SharedPreferences sp=getSharedPreferences("PhoneUser",MODE_PRIVATE);
                    Editor ed=sp.edit();
                    ed.clear();
                    ed.commit();
                    Intent intent= new Intent();
                    intent.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
				}
			});
			
			finish=(LinearLayout) findViewById(R.id.app_finish_layout);
			finish.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
						{
							System.exit(0);
						}
				});
		}
        //初始化
		private void init(){
			layout =  (LinearLayout) findViewById(R.id.datelayout);
			dateText=(TextView) findViewById(R.id.dateText);
			topMenuButton=(ImageView) findViewById(R.id.topButton);
			queryBtn=(Button) findViewById(R.id.queryBtn);
			start_staion_layout=(LinearLayout) findViewById(R.id.start_station_layout);
			end_station_layout=(LinearLayout) findViewById(R.id.end_station_layout);
			main_start_station=(TextView) findViewById(R.id.main_start_station);
			main_end_station=(TextView) findViewById(R.id.main_end_station);
			main_topText=(TextView) findViewById(R.id.main_top_textview);
			main_topText.setText("车票查询");
			//设置slidingMenu
			slidingMenu= new SlidingMenu(this);
			slidingMenu.setBehindOffsetRes(R.dimen.slidingmenuwidth);
			slidingMenu.setMode(SlidingMenu.LEFT);
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
			//指定xml为内容
			slidingMenu.setMenu(R.layout.menu);
			
			//设置时间默认为今天
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.get(Calendar.HOUR_OF_DAY) - 1);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd");
			String now = format.format(calendar.getTime());
			dateText.setText(now);
		}
		public class PopupWindows extends PopupWindow {

			public PopupWindows(Context mContext, View parent) {

				View view = View.inflate(mContext, R.layout.popupwindow_calendar,
						null);
				view.startAnimation(AnimationUtils.loadAnimation(mContext,
						R.anim.fade_in));
				LinearLayout ll_popup = (LinearLayout) view
						.findViewById(R.id.ll_popup);
				ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
						R.anim.push_bottom_in_1));

				setWidth(LayoutParams.FILL_PARENT);
				setHeight(LayoutParams.FILL_PARENT);
				setBackgroundDrawable(new BitmapDrawable());
				setFocusable(true);
				setOutsideTouchable(true);
				setContentView(view);
				showAtLocation(parent, Gravity.BOTTOM, 0, 0);
				update();

				final TextView popupwindow_calendar_month = (TextView) view
						.findViewById(R.id.popupwindow_calendar_month);
				final KCalendar calendar = (KCalendar) view
						.findViewById(R.id.popupwindow_calendar);
				Button popupwindow_calendar_bt_enter = (Button) view
						.findViewById(R.id.popupwindow_calendar_bt_enter);

				popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
						+ calendar.getCalendarMonth() + "月");

				if (null != date) {

					int years = Integer.parseInt(date.substring(0,
							date.indexOf("-")));
					int month = Integer.parseInt(date.substring(
							date.indexOf("-") + 1, date.lastIndexOf("-")));
					popupwindow_calendar_month.setText(years + "年" + month + "月");

					calendar.showCalendar(years, month);
					calendar.setCalendarDayBgColor(date,
							R.drawable.calendar_date_focused);				
				}
				
				List<String> list = new ArrayList<String>(); //设置标记列表
				list.add("2014-04-01");
				list.add("2014-04-02");
				calendar.addMarks(list, 0);

				//监听所选中的日期
				calendar.setOnCalendarClickListener(new OnCalendarClickListener() {

					public void onCalendarClick(int row, int col, String dateFormat) {
						int month = Integer.parseInt(dateFormat.substring(
								dateFormat.indexOf("-") + 1,
								dateFormat.lastIndexOf("-")));
						
						if (calendar.getCalendarMonth() - month == 1//跨年跳转
								|| calendar.getCalendarMonth() - month == -11) {
							calendar.lastMonth();
							
						} else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
								|| month - calendar.getCalendarMonth() == -11) {
							calendar.nextMonth();
							
						} else {
							calendar.removeAllBgColor(); 
							calendar.setCalendarDayBgColor(dateFormat,
									R.drawable.calendar_date_focused);
							date = dateFormat;//最后返回给全局 date
						}
					}
				});

				//监听当前月份
				calendar.setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
					public void onCalendarDateChanged(int year, int month) {
						popupwindow_calendar_month
								.setText(year + "年" + month + "月");
					}
				});
				
				//上月监听按钮
				RelativeLayout popupwindow_calendar_last_month = (RelativeLayout) view
						.findViewById(R.id.popupwindow_calendar_last_month);
				popupwindow_calendar_last_month
						.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
								calendar.lastMonth();
							}

						});
				
				//下月监听按钮
				RelativeLayout popupwindow_calendar_next_month = (RelativeLayout) view
						.findViewById(R.id.popupwindow_calendar_next_month);
				popupwindow_calendar_next_month
						.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
								calendar.nextMonth();
							}
						});
				
				//关闭窗口
				popupwindow_calendar_bt_enter
						.setOnClickListener(new OnClickListener() {

							public void onClick(View v) {
								if(date!=null){
									 try {
										Date today= new Date();
										Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
										Calendar calendar = Calendar.getInstance();
										calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) +3 );
										SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
										String threeDaysLaterStr=formatter.format(calendar.getTime());
										Date threeDaysLater= new SimpleDateFormat("yyyy-MM-dd").parse(threeDaysLaterStr);
										
										if(today.before(dt) && dt.before(threeDaysLater)){
											 dateText.setText(date);
										}else{
											Toast.makeText(MainActivity.this,"只能选择今天到三天后以内的日期！", Toast.LENGTH_SHORT).show();
										}
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 
								}
								dismiss();
							}
						});
			}
		}
        
		
		@Override
		protected void onActivityResult(int requestCode,
				int resultCode,Intent data){
			
			if(requestCode==REQUEST_CODE){
				if(resultCode==RESULT_OK){
					Bundle bd=data.getExtras();
					String start_station=null;
					String end_station=null;
					if(bd!=null){
						start_station=bd.getString("start_station");
						end_station=bd.getString("end_station");
					}
					if(start_station!=null){
						main_start_station.setText(start_station);
					}
					if(end_station!=null){
						main_end_station.setText(end_station);
					}
				}
			}
		}
		

	}
