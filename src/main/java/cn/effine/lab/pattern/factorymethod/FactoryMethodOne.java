/**
 * @author effine
 * @date 2013年9月10日  上午9:29:02
 */

package cn.effine.lab.pattern.factorymethod;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * 具体工厂角色类
 */
public class FactoryMethodOne extends AbstractFactory {

    @Override
    public <T extends ICar> T createCar() {
        return null;
    }
}
