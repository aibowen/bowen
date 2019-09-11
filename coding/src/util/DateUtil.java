/**
 * 
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bowen
 *	2018-08-29
 */
public class DateUtil {
	private static final String yyyy_mm_dd="yyyy-mm-dd";
	private static final String time_stamp="yyyy-MM-dd hh:mm:ss";
	public static Date parseString(String time){
		try {
			return new SimpleDateFormat(yyyy_mm_dd).parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String timeString(Object obj){
		return new SimpleDateFormat(time_stamp).format(obj);
	}
}
