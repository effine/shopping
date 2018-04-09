/**
 * @author effine
 * @date 2013年9月10日  上午9:42:21
 */

package cn.effine.lab.pattern.factorymethod;

/*具体产品角色类：汽车-捷豹-Jaguar*/
public class Jaguar implements ICar {

    @Override
    public void run() {
        System.out.println("捷豹启动");
    }

    @Override
    public void stop() {
        System.out.println("捷豹停止");
    }
}
