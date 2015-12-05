/**
 * @author effine
 * @Date 2015年12月1日  下午11:51:10
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

	static class OOMObject {
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}

	}

}
