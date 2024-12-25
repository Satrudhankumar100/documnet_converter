package com.doc.utility;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtility {
	
	public static String setTime() {
		String date = LocalDate.now().toString();
		LocalTime time = LocalTime.now();
		String dateTime=date+"P"+time.getHour()+"_"+time.getMinute();
		return dateTime;
	}
	
	
	public static LocalDateTime getTime(String fileName) {
			String dateTime=fileName.split("@")[1];
			String date=dateTime.split("P")[0];
			String time=dateTime.split("P")[1];
			
			//date of year month exactDate separate
			String year = date.split("-")[0];
			String month = date.split("-")[1];
			String exactDate = date.split("-")[2];
			
			//hours and minutes separate
			String hour = time.split("_")[0];
			String minuts = time.split("_")[1];

			return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(exactDate), Integer.parseInt(hour), Integer.parseInt(minuts));
		
	}
	

}
