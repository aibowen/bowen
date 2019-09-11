/**
 * 
 */
package concurrency.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 	计算1..n的和
 * @author bowen
 *	2018-08-31
 */
public class ForkJoinTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		invoke2();
	}
	
	public static void invoke1(){
		SumTask task=new SumTask(1,10);
		ForkJoinPool pool=new ForkJoinPool();
		ForkJoinTask<Integer> result=pool.submit(task);
		try {
			System.out.println("result is:"+result.get());
		} catch (InterruptedException |ExecutionException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}
	
	public static void invoke2(){
		SumTask2 task=new SumTask2(1,1000000);
		ForkJoinPool pool=new ForkJoinPool();
		ForkJoinTask<Integer> result=pool.submit(task);
		try {
			System.out.println("result is:"+result.get());
		} catch (InterruptedException |ExecutionException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}

}



class SumTask extends RecursiveTask<Integer>{
	private static final int THRESHOLD=2;
	private int start;
	private int end;
	
	public SumTask(int start,int end){
		this.start=start;
		this.end=end;
	}
	@Override
	protected Integer compute() {
		int sum=0;
		if(end-start<=THRESHOLD){
			for (int j = start; j <= end; j++) {
				sum+=j;
			}
		}else{
			int middleNum=(start+end)/2;
			SumTask half1=new SumTask(start,middleNum);
			SumTask half2=new SumTask(middleNum+1,end);
			half1.fork();
			half2.fork();
			int res1=half1.join();
			int res2=half2.join();
			sum=res1+res2;
		}
		return sum;
	}
}

class SumTask2 extends RecursiveTask<Integer>{
	private static final int THRESHOLD=10;
	private int start;
	private int end;
	
	public SumTask2(int start,int end){
		this.start=start;
		this.end=end;
	}
	
	@Override
	protected Integer compute() {
		int sum=0;
		if(end-start<=THRESHOLD){
			for (int i = start; i <= end; i++) {
				sum+=i;
			}
		}else{
			int middleNum=(start+end)/2;
			SumTask2 half1=new SumTask2(start,middleNum);
			SumTask2 half2=new SumTask2(middleNum+1, end);
			invokeAll(half1,half2);
			sum=half1.join()+half2.join();
		}
		return sum;
	}
	
}
