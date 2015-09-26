package com.ppc.calendar_library;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * 日历控件
 * 
 * @author huangyin
 */
@SuppressWarnings("deprecation")
public class KCalendar extends ViewFlipper implements
		android.view.GestureDetector.OnGestureListener {
	public static final int COLOR_BG_WEEK_TITLE = Color.parseColor("#ffeeeeee"); // 星期标题背景颜色
	public static final int COLOR_TX_WEEK_TITLE = Color.parseColor("#ffcc3333"); // 星期标题文字颜色
	public static final int COLOR_TX_THIS_MONTH_DAY = Color
			.parseColor("#aa564b4b"); // 当前月日历数字颜色
	public static final int COLOR_TX_OTHER_MONTH_DAY = Color
			.parseColor("#ffcccccc"); // 其他月日历数字颜色
	public static final int COLOR_TX_THIS_DAY = Color.parseColor("#ff008000"); // 当天日历数字颜色
	public static final int COLOR_BG_THIS_DAY = Color.parseColor("#ffcccccc"); // 当天日历背景颜色
	public static final int COLOR_BG_CALENDAR = Color.parseColor("#ffeeeeee"); // 日历背景色

	private GestureDetector gd; // 手势监听器
	private Animation push_left_in; // 动画-左进
	private Animation push_left_out; // 动画-左出
	private Animation push_right_in; // 动画-右进
	private Animation push_right_out; // 动画-右出

	private int ROWS_TOTAL = 6; // 日历的行数
	private int COLS_TOTAL = 7; // 日历的列数
	private String[][] dates = new String[6][7]; // 当前日历日期
	private float tb;

	private OnCalendarClickListener onCalendarClickListener; // 日历翻页回调
	private OnCalendarDateChangedListener onCalendarDateChangedListener; // 日历点击回调

	private String[] weekday = new String[] { "日", "一", "二", "三", "四", "五", "六" }; // 星期标题

	private int calendarYear; // 日历年份
	private int calendarMonth; // 日历月份
	private Date thisday = new Date(); // 今天
	private Date calendarday; // 日历这个月第一天(1号)

	private LinearLayout firstCalendar; // 第一个日历
	private LinearLayout secondCalendar; // 第二个日历
	private LinearLayout currentCalendar; // 当前显示的日历

	private Map<String, Integer> marksMap = new HashMap<String, Integer>(); // 储存某个日子被标注(Integer
																			// 为bitmap
																			// res
																			// id)
	private Map<String, Integer> dayBgColorMap = new HashMap<String, Integer>(); // 储存某个日子的背景色

	public KCalendar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public KCalendar(Context context) {
		super(context);
		init();
	}

	private void init() {
		setBackgroundColor(COLOR_BG_CALENDAR);
		// 实例化收拾监听器
		gd = new GestureDetector(this);
		// 初始化日历翻动动画
		push_left_in = AnimationUtils.loadAnimation(getContext(),
				R.anim.push_left_in);
		push_left_out = AnimationUtils.loadAnimation(getContext(),
				R.anim.push_left_out);
		push_right_in = AnimationUtils.loadAnimation(getContext(),
				R.anim.push_right_in);
		push_right_out = AnimationUtils.loadAnimation(getContext(),
				R.anim.push_right_out);
		push_left_in.setDuration(400);
		push_left_out.setDuration(400);
		push_right_in.setDuration(400);
		push_right_out.setDuration(400);
		// 初始化第一个日历
		firstCalendar = new LinearLayout(getContext());
		firstCalendar.setOrientation(LinearLayout.VERTICAL);
		firstCalendar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		// 初始化第二个日历
		secondCalendar = new LinearLayout(getContext());
		secondCalendar.setOrientation(LinearLayout.VERTICAL);
		secondCalendar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		// 设置默认日历为第一个日历
		currentCalendar = firstCalendar;
		// 加入ViewFlipper
		addView(firstCalendar);
		addView(secondCalendar);
		// 绘制线条框架
		drawFrame(firstCalendar);
		drawFrame(secondCalendar);
		// 设置日历上的日子(1号)
		calendarYear = thisday.getYear() + 1900;
		calendarMonth = thisday.getMonth();
		calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
		// 填充展示日历
		setCalendarDate();
	}

	private void drawFrame(LinearLayout oneCalendar) {

		// 添加周末线性布局
		LinearLayout title = new LinearLayout(getContext());
		title.setBackgroundColor(COLOR_BG_WEEK_TITLE);
		title.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(-1, 0,
				0.5f);
		Resources res = getResources();
		tb = res.getDimension(R.dimen.historyscore_tb);
		layout.setMargins(0, 0, 0, (int) (tb * 1.2));
		title.setLayoutParams(layout);
		oneCalendar.addView(title);

		// 添加周末TextView
		for (int i = 0; i < COLS_TOTAL; i++) {
			TextView view = new TextView(getContext());
			view.setGravity(Gravity.CENTER);
			view.setText(weekday[i]);
			view.setTextColor(COLOR_TX_WEEK_TITLE);
			view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1));
			title.addView(view);
		}

		// 添加日期布局
		LinearLayout content = new LinearLayout(getContext());
		content.setOrientation(LinearLayout.VERTICAL);
		content.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 7f));
		oneCalendar.addView(content);

		// 添加日期TextView
		for (int i = 0; i < ROWS_TOTAL; i++) {
			LinearLayout row = new LinearLayout(getContext());
			row.setOrientation(LinearLayout.HORIZONTAL);
			row.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, 0, 1));
			content.addView(row);
			// 绘制日历上的列
			for (int j = 0; j < COLS_TOTAL; j++) {
				RelativeLayout col = new RelativeLayout(getContext());
				col.setLayoutParams(new LinearLayout.LayoutParams(0,
						LayoutParams.MATCH_PARENT, 1));
				col.setBackgroundResource(R.drawable.calendar_day_bg);
				row.addView(col);
				// 给每一个日子加上监听
				col.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						ViewGroup parent = (ViewGroup) v.getParent();
						int row = 0, col = 0;

						// 获取列坐标
						for (int i = 0; i < parent.getChildCount(); i++) {
							if (v.equals(parent.getChildAt(i))) {
								col = i;
								break;
							}
						}
						// 获取行坐标
						ViewGroup pparent = (ViewGroup) parent.getParent();
						for (int i = 0; i < pparent.getChildCount(); i++) {
							if (parent.equals(pparent.getChildAt(i))) {
								row = i;
								break;
							}
						}
						if (onCalendarClickListener != null) {
							onCalendarClickListener.onCalendarClick(row, col,
									dates[row][col]);
						}
					}
				});
			}
		}
	}

	/**
	 * 填充日历(包含日期、标记、背景等)
	 */
	private void setCalendarDate() {
		// 根据日历的日子获取这一天是星期几
		int weekday = calendarday.getDay();
		// 每个月第一天
		int firstDay = 1;
		// 每个月中间号,根据循环会自动++
		int day = firstDay;
		// 每个月的最后一天
		int lastDay = getDateNum(calendarday.getYear(), calendarday.getMonth());
		// 下个月第一天
		int nextMonthDay = 1;
		int lastMonthDay = 1;

		// 填充每一个空格
		for (int i = 0; i < ROWS_TOTAL; i++) {
			for (int j = 0; j < COLS_TOTAL; j++) {
				// 这个月第一天不是礼拜天,则需要绘制上个月的剩余几天
				if (i == 0 && j == 0 && weekday != 0) {
					int year = 0;
					int month = 0;
					int lastMonthDays = 0;
					// 如果这个月是1月，上一个月就是去年的12月
					if (calendarday.getMonth() == 0) {
						year = calendarday.getYear() - 1;
						month = Calendar.DECEMBER;
					} else {
						year = calendarday.getYear();
						month = calendarday.getMonth() - 1;
					}
					// 上个月的最后一天是几号
					lastMonthDays = getDateNum(year, month);
					// 第一个格子展示的是几号
					int firstShowDay = lastMonthDays - weekday + 1;
					// 上月
					for (int k = 0; k < weekday; k++) {
						lastMonthDay = firstShowDay + k;
						RelativeLayout group = getDateView(0, k);
						group.setGravity(Gravity.CENTER);
						TextView view = null;
						if (group.getChildCount() > 0) {
							view = (TextView) group.getChildAt(0);
						} else {
							LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
									-1, -1);
							view = new TextView(getContext());
							view.setLayoutParams(params);
							view.setGravity(Gravity.CENTER);
							group.addView(view);
						}
						view.setText(Integer.toString(lastMonthDay));
						view.setTextColor(COLOR_TX_OTHER_MONTH_DAY);
						dates[0][k] = format(new Date(year, month, lastMonthDay));
						// 设置日期背景色
						if (dayBgColorMap.get(dates[0][k]) != null) {
							// view.setBackgroundResource(dayBgColorMap
							// .get(dates[i][j]));
						} else {
							view.setBackgroundColor(Color.TRANSPARENT);
						}
						// 设置标记
						setMarker(group, 0, k);
					}
					j = weekday - 1;
					// 这个月第一天是礼拜天，不用绘制上个月的日期，直接绘制这个月的日期
				} else {
					RelativeLayout group = getDateView(i, j);
					group.setGravity(Gravity.CENTER);
					TextView view = null;
					if (group.getChildCount() > 0) {
						view = (TextView) group.getChildAt(0);
					} else {
						LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
								-1, -1);
						view = new TextView(getContext());
						view.setLayoutParams(params);
						view.setGravity(Gravity.CENTER);
						group.addView(view);
					}

					// 本月
					if (day <= lastDay) {
						dates[i][j] = format(new Date(calendarday.getYear(),
								calendarday.getMonth(), day));
						view.setText(Integer.toString(day));
						// 当天
						if (thisday.getDate() == day
								&& thisday.getMonth() == calendarday.getMonth()
								&& thisday.getYear() == calendarday.getYear()) {
							view.setText("今天");
							view.setTextColor(COLOR_TX_WEEK_TITLE);
							view.setBackgroundColor(Color.TRANSPARENT);
						} else {
							view.setTextColor(COLOR_TX_THIS_MONTH_DAY);
							view.setBackgroundColor(Color.TRANSPARENT);
						}
						// 上面首先设置了一下默认的"当天"背景色，当有特殊需求时，才给当日填充背景色
						// 设置日期背景色
						if (dayBgColorMap.get(dates[i][j]) != null) {
							view.setTextColor(Color.WHITE);
							view.setBackgroundResource(dayBgColorMap
									.get(dates[i][j]));
						}
						// 设置标记
						setMarker(group, i, j);
						day++;
						// 下个月
					} else {
						if (calendarday.getMonth() == Calendar.DECEMBER) {
							dates[i][j] = format(new Date(
									calendarday.getYear() + 1,
									Calendar.JANUARY, nextMonthDay));
						} else {
							dates[i][j] = format(new Date(
									calendarday.getYear(),
									calendarday.getMonth() + 1, nextMonthDay));
						}
						view.setText(Integer.toString(nextMonthDay));
						view.setTextColor(COLOR_TX_OTHER_MONTH_DAY);
						// 设置日期背景色
						if (dayBgColorMap.get(dates[i][j]) != null) {
							// view.setBackgroundResource(dayBgColorMap
							// .get(dates[i][j]));
						} else {
							view.setBackgroundColor(Color.TRANSPARENT);
						}
						// 设置标记
						setMarker(group, i, j);
						nextMonthDay++;
					}
				}
			}
		}
	}

	/**
	 * onClick接口回调
	 */
	public interface OnCalendarClickListener {
		void onCalendarClick(int row, int col, String dateFormat);
	}

	/**
	 * ondateChange接口回调
	 */
	public interface OnCalendarDateChangedListener {
		void onCalendarDateChanged(int year, int month);
	}

	/**
	 * 根据具体的某年某月，展示一个日历
	 * 
	 * @param year
	 * @param month
	 */
	public void showCalendar(int year, int month) {
		calendarYear = year;
		calendarMonth = month - 1;
		calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
		setCalendarDate();
	}

	/**
	 * 根据当前月，展示一个日历
	 * 
	 * @param year
	 * @param month
	 */
	public void showCalendar() {
		Date now = new Date();
		calendarYear = now.getYear() + 1900;
		calendarMonth = now.getMonth();
		calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
		setCalendarDate();
	}

	/**
	 * 下一月日历
	 */
	public synchronized void nextMonth() {
		// 改变日历上下顺序
		if (currentCalendar == firstCalendar) {
			currentCalendar = secondCalendar;
		} else {
			currentCalendar = firstCalendar;
		}
		// 设置动画
		setInAnimation(push_left_in);
		setOutAnimation(push_left_out);
		// 改变日历日期
		if (calendarMonth == Calendar.DECEMBER) {
			calendarYear++;
			calendarMonth = Calendar.JANUARY;
		} else {
			calendarMonth++;
		}
		calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
		// 填充日历
		setCalendarDate();
		// 下翻到下一月
		showNext();
		// 回调
		if (onCalendarDateChangedListener != null) {
			onCalendarDateChangedListener.onCalendarDateChanged(calendarYear,
					calendarMonth + 1);
		}
	}

	/**
	 * 上一月日历
	 */
	public synchronized void lastMonth() {
		if (currentCalendar == firstCalendar) {
			currentCalendar = secondCalendar;
		} else {
			currentCalendar = firstCalendar;
		}
		setInAnimation(push_right_in);
		setOutAnimation(push_right_out);
		if (calendarMonth == Calendar.JANUARY) {
			calendarYear--;
			calendarMonth = Calendar.DECEMBER;
		} else {
			calendarMonth--;
		}
		calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
		setCalendarDate();
		showPrevious();
		if (onCalendarDateChangedListener != null) {
			onCalendarDateChangedListener.onCalendarDateChanged(calendarYear,
					calendarMonth + 1);
		}
	}

	/**
	 * 获取日历当前年份
	 */
	public int getCalendarYear() {
		return calendarday.getYear() + 1900;
	}

	/**
	 * 获取日历当前月份
	 */
	public int getCalendarMonth() {
		return calendarday.getMonth() + 1;
	}

	/**
	 * 在日历上做一个标记
	 * 
	 * @param date
	 *            日期
	 * @param id
	 *            bitmap res id
	 */
	public void addMark(Date date, int id) {
		addMark(format(date), id);
	}

	/**
	 * 在日历上做一个标记
	 * 
	 * @param date
	 *            日期
	 * @param id
	 *            bitmap res id
	 */
	public void addMark(String date, int id) {
		marksMap.put(date, id);
		setCalendarDate();
	}

	/**
	 * 在日历上做一组标记
	 * 
	 * @param date
	 *            日期
	 * @param id
	 *            bitmap res id
	 */
	public void addMarks(Date[] date, int id) {
		for (int i = 0; i < date.length; i++) {
			marksMap.put(format(date[i]), id);
		}
		setCalendarDate();
	}

	/**
	 * 在日历上做一组标记
	 * 
	 * @param date
	 *            日期
	 * @param id
	 *            bitmap res id
	 */
	public void addMarks(List<String> date, int id) {
		for (int i = 0; i < date.size(); i++) {
			marksMap.put(date.get(i), id);
		}
		setCalendarDate();
	}

	/**
	 * 移除日历上的标记
	 */
	public void removeMark(Date date) {
		removeMark(format(date));
	}

	/**
	 * 移除日历上的标记
	 */
	public void removeMark(String date) {
		marksMap.remove(date);
		setCalendarDate();
	}

	/**
	 * 移除日历上的所有标记
	 */
	public void removeAllMarks() {
		marksMap.clear();
		setCalendarDate();
	}

	/**
	 * 设置日历具体某个日期的背景色
	 * 
	 * @param date
	 * @param color
	 */
	public void setCalendarDayBgColor(Date date, int color) {
		setCalendarDayBgColor(format(date), color);
	}

	/**
	 * 设置日历具体某个日期的背景色
	 * 
	 * @param date
	 * @param color
	 */
	public void setCalendarDayBgColor(String date, int color) {
		dayBgColorMap.put(date, color);
		setCalendarDate();
	}

	/**
	 * 设置日历一组日期的背景色
	 * 
	 * @param date
	 * @param color
	 */
	public void setCalendarDaysBgColor(List<String> date, int color) {
		for (int i = 0; i < date.size(); i++) {
			dayBgColorMap.put(date.get(i), color);
		}
		setCalendarDate();
	}

	/**
	 * 设置日历一组日期的背景色
	 * 
	 * @param date
	 * @param color
	 */
	public void setCalendarDayBgColor(String[] date, int color) {
		for (int i = 0; i < date.length; i++) {
			dayBgColorMap.put(date[i], color);
		}
		setCalendarDate();
	}

	/**
	 * 移除日历具体某个日期的背景色
	 * 
	 * @param date
	 * @param color
	 */
	public void removeCalendarDayBgColor(Date date) {
		removeCalendarDayBgColor(format(date));
	}

	/**
	 * 移除日历具体某个日期的背景色
	 * 
	 * @param date
	 * @param color
	 */
	public void removeCalendarDayBgColor(String date) {
		dayBgColorMap.remove(date);
		setCalendarDate();
	}

	/**
	 * 移除日历具体某个日期的背景色
	 * 
	 * @param date
	 * @param color
	 */
	public void removeAllBgColor() {
		dayBgColorMap.clear();
		setCalendarDate();
	}

	/**
	 * 根据行列号获得包装每一个日子的LinearLayout
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getDate(int row, int col) {
		return dates[row][col];
	}

	/**
	 * 某天是否被标记了
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public boolean hasMarked(String date) {
		return marksMap.get(date) == null ? false : true;
	}

	/**
	 * 清除所有标记以及背景
	 */
	public void clearAll() {
		marksMap.clear();
		dayBgColorMap.clear();
	}

	/***********************************************
	 * 
	 * private methods
	 * 
	 **********************************************/
	// 设置标记
	private void setMarker(RelativeLayout group, int i, int j) {
		int childCount = group.getChildCount();
		if (marksMap.get(dates[i][j]) != null) {
			if (childCount < 2) {
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
						(int) (tb * 0.7), (int) (tb * 0.7));
				params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				params.setMargins(0, 0, 1, 1);
				ImageView markView = new ImageView(getContext());
				markView.setImageResource(marksMap.get(dates[i][j]));
				markView.setLayoutParams(params);
				markView.setBackgroundResource(R.drawable.calendar_bg_tag);
				group.addView(markView);
			}
		} else {
			if (childCount > 1) {
				group.removeView(group.getChildAt(1));
			}
		}

	}

	/**
	 * 计算某年某月有多少天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int getDateNum(int year, int month) {
		Calendar time = Calendar.getInstance();
		time.clear();
		time.set(Calendar.YEAR, year + 1900);
		time.set(Calendar.MONTH, month);
		return time.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 根据行列号获得包装每一个日子的LinearLayout
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private RelativeLayout getDateView(int row, int col) {
		return (RelativeLayout) ((LinearLayout) ((LinearLayout) currentCalendar
				.getChildAt(1)).getChildAt(row)).getChildAt(col);
	}

	/**
	 * 将Date转化成字符串->2013-3-3
	 */
	private String format(Date d) {
		return addZero(d.getYear() + 1900, 4) + "-"
				+ addZero(d.getMonth() + 1, 2) + "-" + addZero(d.getDate(), 2);
	}

	// 2或4
	private static String addZero(int i, int count) {
		if (count == 2) {
			if (i < 10) {
				return "0" + i;
			}
		} else if (count == 4) {
			if (i < 10) {
				return "000" + i;
			} else if (i < 100 && i > 10) {
				return "00" + i;
			} else if (i < 1000 && i > 100) {
				return "0" + i;
			}
		}
		return "" + i;
	}

	/***********************************************
	 * 
	 * Override methods
	 * 
	 **********************************************/
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (gd != null) {
			if (gd.onTouchEvent(ev))
				return true;
		}
		return super.dispatchTouchEvent(ev);
	}

	public boolean onTouchEvent(MotionEvent event) {
		return this.gd.onTouchEvent(event);
	}

	public boolean onDown(MotionEvent e) {
		return false;
	}

	public void onShowPress(MotionEvent e) {
	}

	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// 向左/上滑动
		if (e1.getX() - e2.getX() > 20) {
			nextMonth();
		}
		// 向右/下滑动
		else if (e1.getX() - e2.getX() < -20) {
			lastMonth();
		}
		return false;
	}

	/***********************************************
	 * 
	 * get/set methods
	 * 
	 **********************************************/

	public OnCalendarClickListener getOnCalendarClickListener() {
		return onCalendarClickListener;
	}

	public void setOnCalendarClickListener(
			OnCalendarClickListener onCalendarClickListener) {
		this.onCalendarClickListener = onCalendarClickListener;
	}

	public OnCalendarDateChangedListener getOnCalendarDateChangedListener() {
		return onCalendarDateChangedListener;
	}

	public void setOnCalendarDateChangedListener(
			OnCalendarDateChangedListener onCalendarDateChangedListener) {
		this.onCalendarDateChangedListener = onCalendarDateChangedListener;
	}

	public Date getThisday() {
		return thisday;
	}

	public void setThisday(Date thisday) {
		this.thisday = thisday;
	}

	public Map<String, Integer> getDayBgColorMap() {
		return dayBgColorMap;
	}

	public void setDayBgColorMap(Map<String, Integer> dayBgColorMap) {
		this.dayBgColorMap = dayBgColorMap;
	}
}