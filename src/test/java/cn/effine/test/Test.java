/**
 * @author effine
 * @Date 2015年10月28日  上午10:44:47
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

public class Test {

	public static void main(String[] args) {
		Integer i = 9;
		String str = String.valueOf(i);
		String s = i.toString();
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