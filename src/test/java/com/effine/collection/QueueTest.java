/**
 * @Date 2015年3月12日  下午2:28:57
 * @author 张亚飞
 * @email verphen@163.com
 */

package com.effine.collection;

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
	}
}