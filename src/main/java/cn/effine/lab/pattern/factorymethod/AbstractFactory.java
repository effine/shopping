/**
 * @author effine
 * @date 2013年9月10日  下午1:56:48
 */

package cn.effine.lab.pattern.factorymethod;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 抽象的工厂类
 */
public abstract class AbstractFactory {

    /**
     * 抽象方法使用泛型（T为ICar的实现类）
     *
     * @param <T>
     * @return
     */
    public abstract <T extends ICar> T createCar();
}
