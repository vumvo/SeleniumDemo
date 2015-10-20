package com.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {
	public static String generateUniqueString(){
		  DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		   //get current date time with Date()
		   Date date = new Date();
		   return dateFormat.format(date);
	 
		   //get current date time with Calendar()
		   //Calendar cal = Calendar.getInstance();
		   //System.out.println(dateFormat.format(cal.getTime()));
	}
}
