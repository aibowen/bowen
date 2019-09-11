/**
 * 
 */
package concurrency.threadPool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author bowen
 *	2018-08-28
 */
public class ThreadPoolTest {
	public static void main(String[] args) throws Exception{
		try(Scanner in=new Scanner(System.in)){
			System.out.print("Enter a directory (e.g. /usr/local/jdk5.0/src): ");
			String directory=in.nextLine();
			System.out.print("Enter a keyword (e.g. volatile): ");
			String keyword=in.nextLine();
			
			ExecutorService pool=Executors.newCachedThreadPool();
			
			MatchCounter counter=new MatchCounter(new File(directory),keyword,pool);
			Future<Integer> result=pool.submit(counter);
			
			try {
				System.out.println(result.get()+" matching files.");
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				
			}
			//关闭服务，会先完成已经提交的任务而不再接收新的任务
			pool.shutdown();
			
			int largestPoolSize=((ThreadPoolExecutor)pool).getLargestPoolSize();
			System.out.println("largest pool size "+largestPoolSize);
		}
	}
}
class MatchCounter implements Callable<Integer>{
	
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;
	
	public MatchCounter(File directory,String keyword,ExecutorService pool){
		this.directory=directory;
		this.keyword=keyword;
		this.pool=pool;
	}
	@Override
	public Integer call() throws Exception {
		count=0;
		try {
			File[] files=directory.listFiles();
			List<Future<Integer>> results=new ArrayList<>();
			
			for (File file:files) {
				if(file.isDirectory()){
					MatchCounter counter=new MatchCounter(directory,keyword,pool);
					Future<Integer> result=pool.submit(counter);
					results.add(result);
				}else{
					if(search(file))
						count++;
				}
			}
			
			for (Future<Integer> future : results) {
				try {
					count+=future.get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
		}
		return count;
	}
	
	public boolean search(File file){
		try {
			try(Scanner in=new Scanner(file,"utf-8")){
				boolean found=false;
				while(!found&&in.hasNextLine()){
					String line=in.nextLine();
					if(line.contains(keyword))
						found=true;
				}
				return found;
			}
		} catch (IOException e) {
			return false;
		}
	}
	
}
