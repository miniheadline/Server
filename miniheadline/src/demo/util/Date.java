package demo.util;

public class Date {
	private Integer year;
	private Integer month;
	private Integer day;
	
	public Date(int year, int month, int day) {
		this.year = year;
		this.month= month;
		this.day  = day;
	}
	
	void set(Integer year, Integer month, Integer day) {
		this.year = year;
		this.month= month;
		this.day  = day;
	}
	
	static boolean isValid(int year, int month, int day) {
		
		if (year < 0 || year > 3000) return false;
		if (month < 0 || month > 12) return false;
		if (day < 0 || day > 31) return false;
		
		switch(month) {
		
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			
			return (day >= 1 && day <= 31);
		
		case 4:
		case 6:
		case 9:
		case 11:
			
			return (day >= 1 && day <= 30);
					
		case 2:
			
			if ( year%400 ==0 || (year%4==0 && year%100 !=0) ) {
				return (day >= 1 && day <= 29);
			}
			else {
				return (day >= 1 && day <= 28);
			}
		
		}
			
		return true;
	}
	
	
	void setYear(Integer year) { this.year = year; }
	
	void setMonth(Integer month) { this.month = month; }
	
	void setDay(Integer day) { this.day = day; }
	
	Integer getYear()  { return year; }
	
	Integer getMonth() { return month; }
	
	Integer getDay()   { return day; }
	
}
