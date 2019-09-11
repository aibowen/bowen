/**
 * 
 */
package test;

import test.ISay;
/**
 * @author bowen
 *
 */
public class InstantiateISay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ISay(){
			public void sayHello(){
				System.out.println("hello java");
			}
		}.sayHello();
	}

}
