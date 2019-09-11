/**
 * 
 */
package concurrency.threadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 	jdk对定时任务调度的线程池支持
 * @author bowen
 *	2018-08-31
 */
public class ScheduledExecutorServiceDemo implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScheduledExecutorService scheduled=Executors.newScheduledThreadPool(10);
		scheduled.scheduleAtFixedRate(new ScheduledExecutorServiceDemo(), 1000, 2000, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {
		System.out.println("定时调度线程池示例："+Thread.currentThread().getName()+"\t"+new Date());
	}

}
