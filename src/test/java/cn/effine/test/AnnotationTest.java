/**
 * @author effine
 * @Date 2015年11月4日  下午3:55:57
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationTest {

    public static void main(String[] args) {
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings("unchecked")
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        @SuppressWarnings("rawtypes")
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }

    @Override
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 1)
    public String toString() {
        return "Overriden toString method";
    }

    @Deprecated
    public String method() {
        return null;
    }
}
