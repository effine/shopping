/**
 * @author effine
 * @date 2013年10月4日  下午11:27:18
 */

package cn.effine.lab.pattern.observer;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * observer pattern test class
 */
public class TestWitch {
    public static void main(String[] args) {

        // witched : girl
        IWitched girl = new ConcreteWitched();

        // witchers
        IWitchers witcher1 = new ConcreteWitchers();
        IWitchers witcher2 = new ConcreteWitchers();
        IWitchers witcher3 = new ConcreteWitchers();

        // witched add witchers
        girl.addWitcher(witcher1);
        girl.addWitcher(witcher2);
        girl.addWitcher(witcher3);

        girl.notifyWitcher(args);
    }
}
