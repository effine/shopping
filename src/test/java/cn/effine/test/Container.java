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
}
