/**
 * @author effine
 * @date 2014年4月7日   下午11:48:30
 */

package cn.effine.lab.pattern.adapter;

public class ClassAdapter extends Adaptee implements Target {

    /**
     * 由于源类（客户端类）没有方法method2(),因此适配器补上该方法
     */
    @Override
    public void method2() {
        /* method body */
    }
}
