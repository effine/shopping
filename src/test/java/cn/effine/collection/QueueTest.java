/**
 * @Date 2015年3月12日  下午2:28:57
 * @author effine
 * @email iballader@gmail.com
 */

package cn.effine.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		queue.offer("Hello");
		queue.offer("World!");
		queue.offer("你好！");
		System.out.println(queue.size());
		String str;
		while ((str = queue.poll()) != null) {
			System.out.print(str);
		}
		System.out.println();
		System.out.println(queue.size());
		
		System.err.println("--------------------");
		Queue<String> queue1 = new LinkedList<String>();
		queue1.add("111");
		queue1.add("22");
		queue1.add("33");
//		String str1 = queue1.element();
//		String str2 = queue1.peek();
	}
}