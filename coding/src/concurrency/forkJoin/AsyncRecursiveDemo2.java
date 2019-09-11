/**
 * 
 */
package concurrency.forkJoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author bowen
 *	2018-08-31
 */
public class AsyncRecursiveDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		AsyncTask2 task = new AsyncTask2("d:/Java/jdk1.8.0_111", "exe");
		
		pool.execute(task);
		
		pool.shutdown();
		List<String> list = task.join();
		System.out.println("共找到符合文件的数量"+list.size());
	}

}

class AsyncTask2 extends RecursiveTask<List<String>>{

	public AsyncTask2(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<String> compute() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

