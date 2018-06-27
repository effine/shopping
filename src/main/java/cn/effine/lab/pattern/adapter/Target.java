/**
 * @author effine
 * @date 2014年4月7日   下午11:44:58
 */

package cn.effine.lab.pattern.adapter;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * 目标角色
 */
public interface Target {

    /**
     * 这是源类（客户端类）也有的方法method1()
     */
    void method1();


    /**
     * 这是源类（客户端类）没有的方法method2()
     */
    void method2();
}
