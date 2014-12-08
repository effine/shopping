/**
 * @author verphen
 * @date 2014年12月9日  上午12:41:10
 */

package com.verphen.dao.impl;

public class ReflectTest {
	public static void main(String[] args) {
		
		// one
		Class c1 = ReflectTest1.class;
		
		// two
		ReflectTest1 rt = new ReflectTest1();
		Class c2 = rt.getClass();
		System.err.println(c1==c2);
		
		// three
		try {
			Class c3 = Class.forName("com.verphen.dao.impl.ReflectTest1");
			System.err.println(c3==c2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
}

class ReflectTest1 {

	public static void say() {
		System.err.println("say hello !");
	}

}
