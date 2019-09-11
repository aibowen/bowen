/**
 * 
 */
package test;

import java.util.List;

/**
 * @author bowen
 *
 */
public class PrintUtil {
	public static void showList(List list){
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i));
	}
	
	public static void showListDetail(List list){
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i).toString());
	}
}
