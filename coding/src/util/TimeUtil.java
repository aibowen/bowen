/**
 * 
 */
package util;

import java.util.Calendar;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
/**
 * @author bowen
 *
 */
public class TimeUtil {
	public static List<String> getDayByMonth(int yearParam,int monthParam){
		List<String> list=new ArrayList<String>();
		Calendar calendar=Calendar.getInstance(Locale.CHINA);
		calendar.set(yearParam,monthParam,1);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		int days=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("year: "+year);
		System.out.println(String.valueOf(year)+"年"+month+"月：");
		for(int i=1;i<=days;i++){
			String date="";
			if(month<10){
				if(i<10){
					date=String.valueOf(year)+"-0"+month+"-0"+i;
				}else{
					date=String.valueOf(year)+"-0"+month+"-"+i;
				}
			}else{
				if(i<10){
					date=String.valueOf(year)+"-"+month+"-0"+i;
				}else{
					date=String.valueOf(year)+"-"+month+"-"+i;
				}
			}
			System.out.println("第"+i+"天："+date);
			list.add(date);
		}
		return list;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getDayByMonth(2018,2);
		getDayByMonth(2018,4);
	}

}
