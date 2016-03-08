package cn.edu.csu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static long millisecondsToMinutes(long milliseconds){
		return milliseconds/(1000*60);
	}
	
	
	public static String longToDate(long milliseconds){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date= new Date(milliseconds);
		return sdf.format(date);
	}
	
	public static void main(String args[]){
		System.out.println(longToDate(1447095600000L));
	}
}
