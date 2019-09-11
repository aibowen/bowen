package collection.priorityQueue;
/**
 * 
 */

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * 优先级队列示例：
 * 队列中的元素按照任意顺序插入，按照排序的顺序检索，调用remove方法时总是删掉剩余元素中优先级最小的那个元素
 * @author bowen
 *	2018-08-29
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<LocalDate> pq=new PriorityQueue<>();
		
		pq.add(LocalDate.of(1906, 12, 9));
		pq.add(LocalDate.of(1815, 12, 10));
		pq.add(LocalDate.of(1903, 12, 3));
		pq.add(LocalDate.of(1910, 6, 22));
		
		System.out.println("Iterating over elements...");
		for(LocalDate date:pq)
			System.out.println(date);
		System.out.println("removing elements...");
		while(!pq.isEmpty())
			System.out.println(pq.remove());
		for(LocalDate date:pq)
			System.out.println(date);
	}
}
