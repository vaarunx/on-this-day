package on_this_day.on_this_day;

import java.util.ArrayList;

// Java's default date class is used in the pages class, so this class had to be renamed.
class CustomDate {
   private int month;
   private int day;
   private int year;
   public int getMonth() { return month; }
   public void setMonth(int month) { this.month = month; }
   public int getDay() { return day; }
   public void setDay(int day) { this.day = day; }
   public int getYear() { return year; }
   public void setYear(int year) { this.year = year; }
}

class TodaysHistoryInfo {

	ArrayList<Selected> selected_array = new ArrayList<Selected>();
	ArrayList<Birth> births_array = new ArrayList<Birth>();
	ArrayList<Death> deaths_array = new ArrayList<Death>();
	ArrayList<Holiday> holidays_array = new ArrayList<Holiday>();
	ArrayList<Event> events_array = new ArrayList<Event>();
	private short year;
	
	public short getYear() { return year; }
	public void setYear(short year) {
	       this.year = year;
	   }
	public ArrayList<Selected> getSelected() {
	       return selected_array;
	   }
	public void setSelected(ArrayList<Selected> S) {
		this.selected_array = S;
	}
	public ArrayList<Event> getEvents() {
	       return events_array;
	   }
	public void setEvents(ArrayList<Event> E) {
		this.events_array = E;
	}
	public ArrayList<Birth> getBirths() {
	       return births_array;
	   }
	public void setBirths(ArrayList<Birth> B) {
		this.births_array = B;
	}
	public ArrayList<Death> getDeaths() {
	       return deaths_array;
	   }
	public void setDeaths(ArrayList<Death> D) {
		this.deaths_array = D;
	}
	public ArrayList<Holiday> getHolidays() {
	       return holidays_array;
	   }
	public void setHolidays(ArrayList<Holiday> H) {
		this.holidays_array = H;
	}
		   
	}
interface TodaysHistoryRetriever {
	   public TodaysHistoryInfo[] get(CustomDate date);
	}

public class apiClass { }