package cn.effine.lab.design.dynamic.cglib;

/**
 * @author effine
 * @Date 2017-09-19 23:20
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public class CglibDynamicProxyTest {
    public static void main(String[] args) {

        GoodsDao goodsDao = new GoodsDao();

        GoodsDao target = (GoodsDao) new ProxyFactory(goodsDao).getProxyInstance();

        target.save();
    }
}
