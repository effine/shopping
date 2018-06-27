/**
 * @author effine
 * @date 2013-9-9  下午11:23:42
 */

package cn.effine.lab.pattern.singleton;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * 单例模式：饿汉式
 */
public class HungrySingleton {

    /**
     * 饿汉式单例模式,声明的同时初始化对象
     */
    public static HungrySingleton hungry = new HungrySingleton();

    /**
     * 构造方法设置成privae，即实现了单态
     */
    private HungrySingleton() {
    }

    /**
     * 返回一个单态模式的类的实例
     * @return
     */
    public static final HungrySingleton getInstance() {
        return hungry;
    }
}