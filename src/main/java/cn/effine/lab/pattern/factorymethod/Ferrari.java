/**
 * @author effine
 * @date 2013年9月10日  上午9:40:18
 */

package cn.effine.lab.pattern.factorymethod;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 *
 * 具体产品角色类：汽车-法拉利Ferrari
 */
public class Ferrari implements ICar {

    @Override
    public void run() {
        System.out.println("法拉利启动");
    }

    @Override
    public void stop() {
        System.out.println("法拉利停止");
    }
}
