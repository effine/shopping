/**
 * @author effine
 * @date 2013年9月10日  上午9:29:02
 */

package cn.effine.lab.pattern.factorymethod;

/*具体工厂角色类*/
public class FactoryMethodOne extends AbstractFactory {

	@Override
	public <T extends ICar> T createCar() {
		return null;
	}
}
