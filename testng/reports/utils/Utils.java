/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String getCurrentTime() {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"dd-MMM-yyyy hh:mm:ss");
		Date localDate = new Date();
		return localSimpleDateFormat.format(localDate);
	}
}