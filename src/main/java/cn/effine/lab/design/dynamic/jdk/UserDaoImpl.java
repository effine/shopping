package cn.effine.lab.design.dynamic.jdk;

/**
 * 动态代理：目标对象实现类
 *
 * @author effine
 * @Date 2017-09-19 22:57
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public class UserDaoImpl implements UserDao {
    @Override
    public boolean save() {
        System.out.println("目标对象实现类 ->  save() 方法 ！");
        return false;
    }
}
