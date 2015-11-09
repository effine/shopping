/**
 * @author effine
 * @Date 2015年11月4日  下午3:55:57
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.plugin.Signature;

public class AnnotationTest {

	public static void main(String[] args) {
	}

	@Override
	@MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 1)
	public String toString() {
		return "Overriden toString method";
	}

	@Deprecated
	@MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
	public static void oldMethod() {
		System.out.println("old method, don't use it.");
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 10)
	public static void genericsTest() throws FileNotFoundException {
		List l = new ArrayList();
		l.add("abc");
		oldMethod();
	}

	@Deprecated
	public String method() {
		return null;
	}
}
