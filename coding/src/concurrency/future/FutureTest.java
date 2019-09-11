/**
 * 
 */
package concurrency.future;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Callable和Runnable类似，但是有返回值。Callable接口是一个参数化的类型，参数类型时返回值的类型
 * Future保存异步计算的结果
 * FutureTask同时实现了Future和Runnable接口
 * @author bowen
 *	2018-08-28
 */
public class FutureTest {
	public static void main(String[] args) {
		try(Scanner in=new Scanner(System.in)){
			System.out.print("Enter base directory(e.g. /usr/local/jdk5.0/src): ");
			String directory=in.nextLine();
			System.out.print("Enter keyword (e.g. volatile): ");
			String keyword=in.nextLine();
			MatchCounter counter=new MatchCounter(new File(directory),keyword);
			
			FutureTask<Integer> task=new FutureTask<>(counter);
			Thread t=new Thread(task);//it is a Runnable
			t.start();
			try {
				Integer result=task.get();//it is a Future;
				System.out.println(result+" matching files.");
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e){
				
			}
		};
	}
	
}
class MatchCounter implements Callable<Integer>{
	private File directory;
	private String keyword;
	
	public MatchCounter(File directory,String keyword){
		this.directory=directory;
		this.keyword=keyword;
	}
	@Override
	public Integer call() throws Exception {
		int count=0;
		try {
			File[] files=directory.listFiles();
			List<Future<Integer>> results=new ArrayList<>();
			
			for(File file:files){
				if(file.isDirectory()){
					MatchCounter counter=new MatchCounter(file,keyword);
					FutureTask<Integer> task=new FutureTask<>(counter);
					results.add(task);
					Thread t=new Thread(task);
					t.start();
				}else{
					if(search(file))
						count++;
				}
			}
			
			for(Future<Integer> result:results){
				try {
					count+=result.get();
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
