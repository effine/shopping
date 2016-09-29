/**
 * @author effine
 * @Date 2015年11月3日  上午10:38:20
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

public class Container<K, V> {

	private K k;
	private V v;

	public Container(K k, V v) {
		this.k = k;
		this.v = v;
	}

	public K getKey() {
		return k;
	}

	public void setKey(K k) {
		this.k = k;
	}

	public V getValue() {
		return v;
	}

	public void setValue(V v) {
		this.v = v;
	}
	
	
	public static void main(String[] args) {
		
		String str = "<address>过来看看</address><mobile>18119883088</mobile></orderDelivery><orderInvoice><id>100124498</id>";
		str = str.substring(str.indexOf("<mobile>")+8, str.indexOf("</mobile>"));
//		str = str.replaceAll("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$", "11111111111");
		System.out.println(str);
		
		
		
	}
}
