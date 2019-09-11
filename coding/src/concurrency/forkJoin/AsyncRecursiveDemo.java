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
public class AsyncRecursiveDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ForkJoinPool pool=new ForkJoinPool();
		Task task=new Task("d:/Java/jdk1.8.0_111", "exe");
		
		pool.execute(task);
		
		pool.shutdown();
		List<String> list=task.join();
		System.out.println("共找到符合文件的数量"+list.size());
	}

}

class Task extends RecursiveTask<List<String>>{
	private String path;
	private String suffix;
	
	public Task(String path,String suffix){
		this.path=path;
		this.suffix=suffix;
	}

	@Override
	protected List<String> compute() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	protected List<String> compute() {
//		List<String> result=new ArrayList<>();
//		List<Task> tasks=new ArrayList<>();
//		
//		File file=new File(path);
//		File[] files=file.listFiles();
//		for(File f:files){
//			if(f.isDirectory()){
//				Task task=new Task(path,suffix);
//				task.fork();
//				tasks.add(task);
//			}else{
//				String name=f.getName();
//				if(name.endsWith(suffix))
//					result.add(name);
//			}
//		}
//		
//		if(tasks.size()>1){
//			System.out.printf("%s,task size (当前路径有)=%s个文件夹，当前路径是：%s\n", Thread.currentThread().getName(),tasks.size(),path);
//		}
//		
//		for (Task task:tasks) {
//			List<String> join=task.join();
//			result.addAll(join);
//		}
//		
//		return result;
//	}
	
}