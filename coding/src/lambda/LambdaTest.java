/**
 * 
 */
package lambda;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import javax.management.timer.Timer;

/**
 * @author bowen
 *
 */
public class LambdaTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] weeks={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		System.out.println(Arrays.toString(weeks));
		System.out.println("sorted in dictionary order: ");
		Arrays.sort(weeks);
		System.out.println(Arrays.toString(weeks));
		
		System.out.println("sorted by length by lambda");
		Arrays.sort(weeks,(first,second)->first.length()-second.length());
		System.out.println(Arrays.toString(weeks));
		
		System.out.println("sorted by lenght by LengthComparator: ");
		Arrays.sort(weeks,new LengthComparator());
		System.out.println(Arrays.toString(weeks));
//		Timer timer=new Timer(1000,event -> System.out.println("the time is "+new Date()));
//		timer.start();
		
	}
	
}

class LengthComparator implements Comparator<String>{

	@Override
	public int compare(String first, String second) {
		return first.length()-second.length();
	}
	
}
