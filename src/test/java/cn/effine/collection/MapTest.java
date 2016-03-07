/**
 * @Date 2015年3月12日  上午10:45:31
 * @author 张亚飞
 * @email verphen@163.com
 */

package cn.effine.collection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {

	public static void main(String[] args) {

		Map<Object, String> map = new HashMap<Object, String>();
		map.put(1, "one1");
		map.put(2, "two");
		map.put(3, "three");
		map.put(null, null);

		System.err.println(map.containsKey(1));
		System.err.println(map.containsValue(null));
		System.err.println(map.get(null));

		TreeMap<Integer, Integer> map1 = new TreeMap<Integer, Integer>(); // 默认的TreeMap升序排列
		map1.put(1, 2);
		map1.put(2, 4);
		map1.put(7, 1);
		map1.put(5, 2);
		System.out.println("map1=" + map1);

		//================ Integer类型============================
		
		TreeMap<Integer, Integer> map2 = new TreeMap<Integer, Integer>(
				new Comparator<Integer>() {
					/*
					 * int compare(Object o1, Object o2) 返回一个基本类型的整型， 返回负数表示：o1
					 * 小于o2， 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
					 */
					public int compare(Integer a, Integer b) {
						return b - a;	//----------重写算法-----------
					}
				});
		map2.put(1, 2);
		map2.put(2, 4);
		map2.put(7, 1);
		map2.put(5, 2);
		System.out.println("Map2=" + map2);
		
		
		//=============== String类型 =============================
		
		TreeMap<String, String> treeMap2 = new TreeMap<String, String>(
				new Comparator<String>() {

					/*
					 * int compare(Object o1, Object o2) 返回一个基本类型的整型， 返回负数表示：o1
					 * 小于o2， 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
					 */
					public int compare(String o1, String o2) {

						// 指定排序器按照降序排列
						return o2.compareTo(o1);	//----------重写算法-----------
					}
				});
		treeMap2.put("2", "1");
		treeMap2.put("b", "1");
		treeMap2.put("1", "1");
		treeMap2.put("a", "1");
		System.out.println("treeMap2=" + treeMap2);

	}
	
	
}
