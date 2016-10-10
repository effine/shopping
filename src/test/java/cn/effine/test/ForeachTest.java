package cn.effine.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForeachTest {
	public static void main(String[] args) {

		List<Object> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add("3");
		list.add(4);

		List<Map<String, Object>> list1 = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("11", 11);
		map1.put("12", 12);
		list1.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("21", 21);
		map2.put("22", 22);
		list1.add(map2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("31", 31);
		map3.put("32", 322);
		list1.add(map3);

		String[] strArr = { "1", "2", "3" };
		for (String str : strArr) {
			System.out.println(str);
		}

		System.out.println(list1);
	}

	public static List<Object> getLists() {
		return null;
	}
}
