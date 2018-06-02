package com.nz.onlineMonitoring.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static DateUtil du = null;
	
	private DateUtil() {}
	public static DateUtil getInstance(){
		if(du == null){
			synchronized(DateUtil.class){
				if(du == null){
					du = new DateUtil();
				}
			}
		}
		return du;
	}
	
	//生成时间
	public String creatDateTime(){
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		//格式化日期
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		String dateTime = localDateTime.format(format);
		
		return dateTime;
	}
}
