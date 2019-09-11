/**
 * 
 */
package concurrency.forkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * RecursiveAction：用于任务没有返回结果的场景
 * @author bowen
 *	2018-08-31
 */
public class RecursiveActionDemo {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		List<Product> list=new ArrayList<>();
		for (int i = 0; i < 40; i++) {
			Product product=new Product("黑人清凉薄荷",18);
			list.add(product);
		}
		
		ForkJoinPool pool=new ForkJoinPool();
		
		MyTask task=new MyTask(list,0,list.size(),19.9);
		pool.execute(task);
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.MILLISECONDS);
		
		do{
			/**
			 * 
			 * getActiveThreadCount		返回当前正在执行或正在执行任务的线程数的估计值
			 * getStealCount	返回从另一个线程工作队列中窃取的任务总数的估计值。
			 * getParallelism	返回此池的目标并行级别。
			 */
			
            System.out.printf("正在活动的线程数量：%s,窃取任务的数量：%s,并行执行的最大数量：%s\n",pool.getActiveThreadCount(),pool.getStealCount(),pool.getParallelism());
		}while(!task.isDone());
		
		if(task.isCompletedNormally()){
			System.out.println("main:任务完成");
		}
		
		for (int i = 0; i < 40; i++) {
			System.out.println(list.get(i));
		}
	}

}

class MyTask extends RecursiveAction{
	private List<Product> list;
	private int start;
	private int end;
	private double price;
	
	public MyTask(List<Product> list,int start,int end,double price){
		this.list=list;
		this.start=start;
		this.end=end;
		this.price=price;
	}
	@Override
	protected void compute() {
		if(end-start<=10){
			System.out.printf("起始：start:%s,end:%s", start,end);
			update();
		}else{
			int middle=(start+end)/2;
			MyTask task1=new MyTask(list,start,middle,price);
			MyTask task2=new MyTask(list,middle,end,price);
			System.out.printf("分析：start:%s,middle:%s,end:%s", start,middle,end);
			invokeAll(task1,task2);
		}
	}
	
	private void update(){
		for (int i = start; i <= end; i++) {
			Product product=list.get(i);
			product.setPrice(price);
		}
	}
}

class Product{
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString(){
    	return getClass().getName()+"[name="+name+",price="+price+"]";
    }
}
