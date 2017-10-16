package cn.effine.lab.designPattern.dynamicProxy.jdk;

/**
 * 动态代理 测试类
 *
 * @author effine
 * @Date 2017-09-19 22:59
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public class DynamicProxyTest {

    public static void main(String[] args) {

        UserDao dao = new UserDaoImpl();

        System.out.println(dao.getClass());

        UserDao proxy = (UserDao) new DynamicProxy(dao).getInstance();

        proxy.save();



    }
}
