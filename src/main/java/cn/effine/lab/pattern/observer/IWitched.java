/**
 * @author effine
 * @date 2013年10月4日  下午11:02:21
 */

package cn.effine.lab.pattern.observer;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 抽象被观察者角色
 */
public interface IWitched {

    /**
     * 添加观察者
     *
     * @param w
     */
    public void addWitcher(IWitchers w);

    /**
     * 删除观察者
     *
     * @param w
     */
    public void deleteWitcher(IWitchers w);

    /**
     * 被观察者 发生变化，通知观察者
     *
     * @param o
     */
    public void notifyWitcher(Object o);
}
