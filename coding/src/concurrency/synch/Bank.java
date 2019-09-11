/**
 * 
 */
package concurrency.synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bowen
 *	2018-08-28
 */
public class Bank {
	private final double[] accounts;
	private final Lock bankLock;
	private Condition sufficientFunds;
	
	public Bank(int n,double initialBalance){
		accounts=new double[n];
		Arrays.fill(accounts, initialBalance);
		bankLock=new ReentrantLock();
		//返回一个与该锁相关的条件对象
		sufficientFunds=bankLock.newCondition();
	}
	
	public void transfer(int from,int to,double amount)throws InterruptedException{
		bankLock.lock();
		try {
			while(accounts[from]<amount)
				//将该线程放到条件的等待集中
				sufficientFunds.await();
			System.out.println(Thread.currentThread());
			accounts[from]-=amount;
			System.out.printf(" %10.2f from %d to %d", amount,from,to);
			accounts[to]+=amount;
			System.out.printf(" total balance:%10.2f%n",getTotalBalance());
			//解除该条件的等待集中的所有线程的阻塞状态
			sufficientFunds.signalAll();
		} finally {
			bankLock.unlock();
		}
	}
	
	public double getTotalBalance(){
		bankLock.lock();
		try {
			double sum=0;
			for (double d : accounts) {
				sum+=d;
			}
			return sum;
		} finally {
			bankLock.unlock();
		}
	}
}
