package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeUtils {

    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    static DateFormat format=new SimpleDateFormat("yyyy-mm-dd");
    
	public static Date convertDate(Date date) {
		// TODO Auto-generated method stub
		if (date != null) {
	        return date;
		}
		return null;
	}

	public static Date newDateWithYear(int year) {	
	    Calendar calendar = new GregorianCalendar(year,0,1);
		return calendar.getTime();
	}

}
