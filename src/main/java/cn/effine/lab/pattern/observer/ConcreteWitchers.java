/**
 * @author effine
 * @date 2013年10月4日  下午11:16:56
 */

package cn.effine.lab.pattern.observer;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * 具体观察者角色
 */
public class ConcreteWitchers implements IWitchers {

    @Override
    public void update(Object o) {
        System.out.println(o);
    }
}
