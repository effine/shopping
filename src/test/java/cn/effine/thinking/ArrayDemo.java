/**
 * @author verphen
 * @date 2015年4月8日  上午12:27:30
 */

package cn.effine.thinking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDemo {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
		System.out.println(list);
		
		int[] i = {12,2,3,4};
		List<int[]> list1 = Arrays.asList(i);
		System.out.println(list1);
		
	}
}
