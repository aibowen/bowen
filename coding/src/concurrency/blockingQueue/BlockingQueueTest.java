/**
 * 
 */
package concurrency.blockingQueue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author bowen
 *	2018-08-28
 */
public class BlockingQueueTest {
	private static final int FILE_QUEUE_SIZE=10;
	private static final int SEARCH_THREADS=100;
	private static final File DUMMY=new File("");
	private static final BlockingQueue<File> queue=new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
	private static final List list=new ArrayList();
	
	public static void main(String[] args) {
		try(Scanner in=new Scanner(System.in)){
			System.out.print("Enter base directory (e.g. /opt/jdk1.8.0/src): ");
			String directory=in.nextLine();
			System.out.print("Enter keyword (e.g. volatile): ");
			String keyword=in.nextLine();
			Runnable enumerator=()->{
				try {
					enumerate(new File(directory));
					queue.put(DUMMY);
				} catch (InterruptedException e) {
				}
			};
			
			new Thread(enumerator).start();
			for (int i = 0; i < SEARCH_THREADS; i++) {
				Runnable searcher=()->{
					try {
						boolean done=false;
						while(!done){
							File file=queue.take();
							if(file==DUMMY){
								queue.put(file);
								done=true;
							}else
								search(file,keyword);
						}
					} catch (IOException e) {
					} catch (InterruptedException e){
						
					}
				};
				new Thread(searcher).start();
			}
			
			System.out.println("包含关键字的文件的数量："+list.size());
		};
	}
	
	/**
	 * 递归枚举一个给定的目录及它的子目录下的所有文件
	 * @param directory
	 * @throws InterruptedException
	 */
	public static void enumerate(File directory)throws InterruptedException{
		File[] files=directory.listFiles();
		for (File file : files) {
			if(file.isDirectory())
				enumerate(file);
			else
				queue.put(file);
		}
	}
	/**
	 * 根据给定的关键词搜索并返回匹配的行
	 * @param file
	 * @param keyword
	 * @throws IOException
	 */
	public static void search(File file,String keyword)throws IOException{
		try(Scanner in=new Scanner(file,"utf-8")){
			int lineNumber=0;
			while(in.hasNextLine()){
				lineNumber++;
				String line=in.nextLine();
				if(line.contains(keyword)&&!list.contains(file.getPath())){
					list.add(file.getPath());
					System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
				}
			}
		}
	}
	
}
