package tournament;

import java.util.Calendar;
import java.util.GregorianCalendar;

public final class DateUtility
{
    private DateUtility() {}
    
    public static String toFormalDate(Calendar d)
    {
	final String[] months = {
		"January","February","March","April","May","June",
		"July","August","September","October","November","December"
	};
	String s = "";
	s += months[d.get(Calendar.MONTH)] + " ";
	s += d.get(Calendar.DAY_OF_MONTH) + " ";
	s += d.get(Calendar.YEAR);
	return s;
    }
    
    /**
     * Returns a Calendar with all the date set to the parameters
     * @param month
     * @param day
     * @param year
     * @return
     */
    public static Calendar getDate(int month, int day, int year)
    {
	GregorianCalendar cal = new GregorianCalendar();
	
	cal.set(Calendar.MONTH, month);
	cal.set(Calendar.DAY_OF_MONTH, day);
	cal.set(Calendar.YEAR, year);
	
	return cal;
    }
    
    public static Calendar parseNormalDate(String s)
    {
	// Lets break apart the String
	String[] parts = s.split("/");
	int month = Integer.parseInt(parts[0]) - 1;
	int day = Integer.parseInt(parts[1]);
	int year = Integer.parseInt(parts[2]);
	return getDate(month,day,year);
    }
    
    public static String toNormalDate(Calendar d)
    {
	String s = "";
	s += (d.get(Calendar.MONTH)+1) + "/";
	s += d.get(Calendar.DAY_OF_MONTH) + "/";
	s += d.get(Calendar.YEAR);
	return s;
    }
}
