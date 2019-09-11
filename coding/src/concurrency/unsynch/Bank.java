/**
 * 
 */
package concurrency.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bowen
 *	2018-08-28
 *
 *
 */
public class Bank {
	private final double[] accounts;
	
	private Lock bankLock=new ReentrantLock();
	
	public Bank(int n,double initialBalance){
		accounts=new double[n];
		Arrays.fill(accounts, initialBalance);
	}
	
	public void transfer(int from,int to,double amount){
		bankLock.lock();
		try {
			if(accounts[from]<amount)
				return;
			System.out.println(Thread.currentThread());
			accounts[from]-=amount;
			System.out.printf(" %10.2f from %d to %d",amount,from,to);
			accounts[to]+=amount;
			System.out.printf(" total balance: %10.2f%n",getTotalBalance());
		} catch(Exception e){
			
		} finally{
			bankLock.unlock();
		}
		
	}
	
	public double getTotalBalance(){
		double sum=0;
		for(double a:accounts){
			sum+=a;
		}
		return sum;
	}
	
	public int size(){
		return accounts.length;
	}
}
