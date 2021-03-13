package com.give.donagi.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonsDateConverter {
	
	public String getDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷
		
		return dateFormat.format(date);
	}

	public int getDateStatus(Date dateFrom, Date dateTo) {
		
		return eventStatus(dateFrom, dateTo);
	}
	
	public String getDateTime(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return dateTimeFormat.format(date);
	}
	
	public String getTime(Date date) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		return timeFormat.format(date);
	}
	
	public int getDday(Date dDay) {
		Calendar dDayCal = Calendar.getInstance();
		Calendar todayCal = Calendar.getInstance();
		long lDday = 0;
		long lToday = 0;
		double subDay = 0;
		int result = 0;
		
		dDayCal.setTime(dDay);
		lDday = dDayCal.getTimeInMillis() / (24*60*60*1000);
		lToday = todayCal.getTimeInMillis() / (24*60*60*1000);
		
		subDay = Math.ceil(lDday - lToday) + 1;
		
		if(subDay <= 0) {
			
			result = 0;
		
		}else {
			
			result = (int)subDay;
		}
		
		return result;
	}
	
	public Date parseStringToDate(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		
		try {
			
			result = format.parse(dateStr);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	private int eventStatus(Date dateFrom, Date dateTo) {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		Date eventDatefrom = null;
		Date eventDateto = null;
		int status = 0;
		
		cal.setTime(dateTo);
		cal.add(Calendar.DATE, +1);
		
		eventDatefrom = dateFrom;
		eventDateto = cal.getTime();
		
		if(currentDate.before(eventDatefrom)) {
			status = 1; // 준비중
			
		}else if(currentDate.after(eventDatefrom) && currentDate.before(eventDateto)) {
			status = 2; // 진행중
			
		}else {
			status = 3; // 종료
		}
		
		return status;
	}
}
