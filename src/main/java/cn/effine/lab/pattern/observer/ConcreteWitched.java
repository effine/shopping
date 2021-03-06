/**
 * @author effine
 * @date 2013年10月4日  下午11:15:20
 */

package cn.effine.lab.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * 具体被观察者角色
 */
public class ConcreteWitched implements IWitched {

    /**
     * save witchers list
     */
    private List<IWitchers> list = new ArrayList<IWitchers>();

    @Override
    public void addWitcher(IWitchers w) {
        list.add(w);
    }

    @Override
    public void deleteWitcher(IWitchers w) {
        list.remove(w);
    }

    @Override
    public void notifyWitcher(Object o) {
        for (IWitchers l : list) {
            l.update(o);
        }
    }

}
