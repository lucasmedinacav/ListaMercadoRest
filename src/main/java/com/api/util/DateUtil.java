package com.api.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date datePlusOneDay(Date date) {
		Calendar c = Calendar.getInstance();
		Date dtAddOneDay = date;
		c.setTime(dtAddOneDay);
		c.add(Calendar.DATE, 1);
		dtAddOneDay = c.getTime();
		return dtAddOneDay;
	}
	
}
