package com.core.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.core.entity.Staff;
import com.core.entity.main.Enterprise;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils{
	private static String database;

	public static String getDatabase() {
		return database;
	}

	public static void setDatabase(String database) {
		Utils.database = database;
	}
	
	public static String formatPercent(float percent) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(percent);
	}
	public static String createToken(String code,String mobile) {
		String uuid = UUID.randomUUID ().toString ().replace ("-", "");
		return MD5Util.encrypt(uuid+code+mobile);
	}

	public static Gson getGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		return builder.create();
	}

	public static void main(String[] args) {
		String content = "NH01=";
		String pattern = "[A-Z0-9]*";
		boolean isMatch = Pattern.matches(pattern, content);
		System.out.println(isMatch);
	}

	public static String getCurrentUserName(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("currentUser");
		try {
			Staff staff= (Staff) obj;
			return staff.getId()+"";
		} catch (Exception e) {
			Enterprise enterprise= (Enterprise) obj;
			return enterprise.getName();
		}
	}
	
	public static Integer getCurrentUserId(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("currentUser");
		try {
			Staff staff= (Staff) obj;
			return staff.getId();
		} catch (Exception e) {
			return 0;
		}
	}
	public static String getCurrentUserInfo(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("currentUser");
		if (null==obj) {
			return "";
		}
		String code = (String) request.getSession().getAttribute("code");
		try {
			Staff staff= (Staff) obj;
			return code+"-"+staff.getId()+"-"+staff.getName();
		} catch (Exception e) {
			Enterprise enterprise= (Enterprise) obj;
			return code+"-"+enterprise.getId()+"&"+enterprise.getName();
		}
	}

	public static int getProject(HttpServletRequest request) {
		try {
			return (int) request.getSession().getAttribute("current_project");
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean isMatch(String code) {
		String pattern = "[A-Z0-9]*";
		return Pattern.matches(pattern, code);
	}

	public static String ajaxReturn4ajax(boolean success, String msg) {
		return "{\"success\":" + success + ",\"msg\":\"" + msg + "\"}";
	}
	
	public static String ajaxReturn(boolean success, String msg) {
		return "{\"success\":" + success + ",\"msg\":" + msg + "}";
	}
	
	

	public static String ajaxReturn(boolean success) {
		return "{\"success\":" + success + "}";
	}

	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	
	public static String getYesterday() {
		 Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(date);
		 return dateString;
	}
	public static String getCurrentTime2() {
		SimpleDateFormat format = new SimpleDateFormat("HHmmssSSS");
		return format.format(new Date());
	}

	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	private static long orderNum = 0l;
	private static String date;

	public static synchronized String getVoucherNo() {
		String str = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
		if (date == null || !date.equals(str)) {
			date = str;
			orderNum = 0l;
		}
		orderNum++;
		long orderNo = Long.parseLong((date));
		orderNo += orderNum;
		return orderNo + "";
	}
}
