/**
 * @author effine
 * @date 2013年9月10日  上午9:29:02
 */

package cn.effine.lab.pattern.simplefactory;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * <p>
 * 工厂角色类
 */
public class SimpleFactory {

    /**
     * 工厂方法，返回类型为抽象的产品角色
     *
     * @param carType
     * @return
     */
    public static ICar driveCar(String carType) {
        String benz = "Benz";
        String ferrari = "Ferrari";
        String jaguar = "Jaguar";
        if (benz.equalsIgnoreCase(carType)) {
            return new Benz();
        } else if (ferrari.equalsIgnoreCase(carType)) {
            return new Ferrari();
        } else if (jaguar.equalsIgnoreCase(carType)) {
            return new Jaguar();
        } else {
            throw new RuntimeException("没有该汽车类型");
        }
    }
}
