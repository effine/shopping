/**
 * @author effine
 * @date 2013年9月10日  上午9:48:32
 */

package cn.effine.lab.pattern.simplefactory;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * 测试简单工厂方法类
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        ICar car = SimpleFactory.driveCar("Jaguar");

        car.run();
        car.stop();
    }
}
