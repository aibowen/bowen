/**
 * 
 */
package timedtask;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import util.DateUtil;

/**
 * jdk原生定时工具
 * @author bowen
 *	2018-08-31
 */
public class TimerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer timer=new Timer();
		Calendar calendar=Calendar.getInstance();
		System.out.println("now time: "+calendar.getTime());
		calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)+5);
		System.out.println("first time: "+calendar.getTime());
		MyTask task=new MyTask();
		timer.scheduleAtFixedRate(task, calendar.getTime(), 2000);
	}

}

class MyTask extends TimerTask{

	@Override
	public void run() {
		System.out.println("execute time start is: "+DateUtil.timeString(this.scheduledExecutionTime()));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
}
