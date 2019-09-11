package objectAnalyzer;

import java.lang.reflect.Field;
import java.util.ArrayList;

import stream.objectStream.Employee;
import util.DateUtil;

/**
 * 
 */

/**
 * @author bowen
 *	2018-09-07
 */
public class ObjetAnalyzerTest {
	/**
	 * name属性为私有的，get方法将抛出IllegalAccessException，为了取到值需要调用setAccessible()
	 */
	public static void getFieldValue(){
		Employee emp=new Employee("harry cacker",3500,DateUtil.parseString("1989-01-01"));
		Class cl=emp.getClass();
		try {
			Field field=cl.getDeclaredField("name");
			field.setAccessible(true);
			Object obj=field.get(emp);
			System.out.println("the value of name field is "+obj);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> squares=new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			squares.add(i*i);
		}
		System.out.println(new ObjectAnalyzer().toString(squares));
	}

}
