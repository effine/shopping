/**
 * @author verphen
 * @date 2014年12月9日  上午12:41:10
 */

package com.verphen.dao.impl;

import java.util.ArrayList;
import java.util.List;

public class ReflectTest {
	public static void main(String[] args) {
		 
		List<String> list = new ArrayList<String>();
		Class c = list.getClass();
		Class c1 = List.class;
		try {
			Class c2 = Class.forName("java.util.List");
			System.err.println(c==c1);
			System.err.println(c2==c1);
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
