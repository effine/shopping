/**
 * @author effine
 * @Date 2015年10月28日  上午10:44:47
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Class c1 = new ArrayList<Integer>().getClass();
		Class c2 = new ArrayList<String>().getClass();
		System.out.println(c1 == c2);

		Object obj = new Object();
		Integer in = new Integer(1);
		in.toString();
	}

	public String method1() {
		try {
			System.out.println("1");
			return "1";
		} catch (Exception e) {

		} finally {
			System.out.println("2");
			return "2";
		}

	}
}

class ThreadTest extends Thread {

}
