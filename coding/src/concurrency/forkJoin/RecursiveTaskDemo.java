/**
 * 
 */
package concurrency.forkJoin;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author bowen
 *	2018-08-31
 */
public class RecursiveTaskDemo {

	/**
	 * @param args
	 */
//	public static void main(String[] args) throws InterruptedException,ExecutionException{
//		int rows = 10000;
//		int cols = 10000;
//		int number = 8;
//
//		MatrixMock matrix = new MatrixMock(rows,cols,number);
//
//		ForkJoinPool pool = new ForkJoinPool();
//		Task task = new Task(matrix,0,rows,number);
//
//		long start = System.currentTimeMillis();
//		pool.execute(task);
//		System.out.println("RecursiveTask搜索的结果是："+task.get());
//		pool.shutdown();
//		pool.awaitTermination(1, TimeUnit.MILLISECONDS);
//
//		long end = System.currentTimeMillis();
//		System.out.println("RecursiveTask搜索的时间是："+(end-start));
//
//		int counts = 0;
//		start = System.currentTimeMillis();
//		for (int i  =  0; i < rows; i++) {
//			int [] row = matrix.getRow(i);
//			for (int j  =  0; j < row.length; j++) {
//				if (row[j] == number) {
//					counts++;
//				}
//			}
//		}
//		end = System.currentTimeMillis();
//		System.out.println("单线程搜索的结果是："+counts);
//		System.out.println("单线程搜索的时间是："+(end-start));
//	}

}

class Task extends RecursiveTask<Integer>{
	private MatrixMock matrixMock;
	private int start;
	private int end;
	private int number;
	
	public Task(MatrixMock matrixMock,int start,int end,int number){
		this.matrixMock = matrixMock;
		this.start = start;
		this.end = end;
		this.number = number;
	}
	@Override
	protected Integer compute() {
		int result = 0;
		if(end-start<100){
			result = search();
		}else{
			int mid = (start+end)/2;
			Task task1 = new Task(matrixMock,start,mid,number);
			Task task2 = new Task(matrixMock,mid,end,number);
			invokeAll(task1,task2);
			try {
				result = task1.get()+task2.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int search(){
		int result = 0;
		for (int i  =  start; i < end; i++) {
			int[] row = matrixMock.getRow(i);
			for (int j  =  0; j < row.length; j++) {
				if (number == row[j]) {
					result++;
				}

			}
		}
		return result;
	}
	
}

class MatrixMock{
	private int[][] data;
	
	public MatrixMock(int size,int cols,int number){
		data = new int[size][cols];
		Random random = new Random();
		int counter = 0;
		for (int i  =  0; i < size; i++) {
			for (int j  =  0; j < size; j++) {
				data[i][j] = random.nextInt(10);
				if (data[i][j] == number) {
					counter++;
				}
			}
		}
		
		System.out.printf("在矩阵中找到了数字：%d,%d次",number,counter);
	}
	
	public int[] getRow(int rowNum){
		if(rowNum >= 0&&rowNum < data.length){
			return data[rowNum];
		}
		return null;
	}
}
