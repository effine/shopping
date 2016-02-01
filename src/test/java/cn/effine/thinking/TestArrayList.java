/**
 * @author effine
 * @Date 2015年5月8日  下午1:47:28
 * @email iballader#gmail.com
 */

package cn.effine.thinking;

/**
 * 简易版ArrayList
 */
class ArrList {
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void add(Object obj) {
		this.obj = obj;
	}

}

public class TestArrayList {
	public static void main(String[] args) {
		ArrList arrList = new ArrList();
		arrList.add(1);
		arrList.add("1");

		Integer objInt = (Integer) arrList.getObj();
		System.out.println(objInt);
	}
}