package cn.effine.lab.pattern.dynamicproxy.jdk;

/**
 * 动态代理：目标对象
 *
 * @author effine
 * @Date 2017-09-19 22:57
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public interface UserDao {

    /**
     * 保存
     *
     * @return
     */
    boolean save();
}
