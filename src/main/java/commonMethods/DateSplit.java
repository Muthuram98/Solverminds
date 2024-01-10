package commonMethods;

import java.util.Calendar;
import java.util.Date;

public class DateSplit 
{
	public static void main(String a[])
	{
		Date date; // your date
	    Calendar cal = Calendar.getInstance();
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int monthinc=month+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    System.out.println(year+"-----"+monthinc+"----"+day);
	}
}
