package demo.util;

public class DateWithSecond {
	
	private Date date;
	private Integer hour;
	private Integer minute;
	private Integer second;
	
	public DateWithSecond() {
		
	}
	
	public DateWithSecond(Date date, int hour, int minute, int second) {
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public void set(Date date, Integer hour, Integer minute, Integer second) {
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	// input format: yy.mm.dd.hh.mm.ss;
	public void setWithString(String str) {
		
		String[] msg = str.split(".");
		
		int year, month, day, hour, minute, second;
		year = Integer.parseInt(msg[0]); 
		month= Integer.parseInt(msg[1]); 
		day  = Integer.parseInt(msg[2]); 
		hour = Integer.parseInt(msg[3]); 
		minute = Integer.parseInt(msg[4]); 
		second = Integer.parseInt(msg[5]); 
		
		set(new Date(year, month, day), hour, minute, second);
		
	}
	
	static boolean isValid(Date date, int hour, int minute, int second) {
		
		return Date.isValid(date.getYear(), date.getMonth(), date.getDay()) && hour >= 0 && hour <=23
				&& minute >= 0 && minute <= 59 && second >= 0 && second <= 59;
	}
	
	
	public void setYear(Integer year) { date.setYear(year); }
	
	public void setMonth(Integer month) { date.setMonth(month); }
	
	public void setDay(Integer day) { date.setDay(day); }
	
	public Integer getYear()  { return date.getYear(); }
	
	public Integer getMonth() { return date.getMonth(); }
	
	public Integer getDay()   { return date.getDay(); }
	
	public void setHour(Integer hour) { this.hour = hour; }
	
	public void setMinute(Integer minute) { this.minute = minute; }
	
	public void setSecond(Integer second) { this.second = second; }
	
	public Integer getHour()   { return hour; }
	
	public Integer getMinute() { return minute; }
	
	public Integer getSecond() { return second; }
	
}