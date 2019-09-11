/**
 * 
 */
package lambda;

import java.util.function.DoublePredicate;

/**
 * @author bowen
 *
 */
public class PrimeNumber {
	
	public static void getOuShu(DoublePredicate dp){
		for(int i=1;i<=10;i++){
			if(dp.test(i)){
				System.out.println(i);
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getOuShu(i->i%2==0);
	}

}
