package cn.effine.lab.design.dynamic.cglib;

/**
 * @author effine
 * @Date 2017-09-19 23:19
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public class GoodsDao {

    public boolean save() {
        System.out.println("cglib 实现动态代理模式, 调用 save() 方法 ！");
        return true;
    }
}
