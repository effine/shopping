/**
 * @author effine
 * @Date 2015年12月1日  下午11:51:10
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }

    }

    static class OOMObject {
    }
}
