/**
 * @author effine
 * @Date 2015年10月28日  上午10:44:47
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {

		
		List<String> list= new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		list.add("11");
		
		List<String> list1= new ArrayList<String>(8);
		list1.add("age");
		list1.add("gender");
		list1.add("name");
		list1.add("phone1");
		list1.add("phone2");
		list1.add("phone3");
		list1.add("phone4");
		list1.add("phone5");
		list1.add("phone6");
		list1.add("phone7");
		list1.add("phone8");
		list1.add("phone9");
		
		Collections.copy(list1, list);
		
		
		System.out.println(list);
		System.out.println(list1);
		

	}
}
