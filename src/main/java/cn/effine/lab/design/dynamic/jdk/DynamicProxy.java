package cn.effine.lab.design.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 *
 * @author effine
 * @Date 2017-09-19 22:55
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public class DynamicProxy {


    //TODO  研究动态代理： http://www.cnblogs.com/cenyu/p/6289209.html
    //      http://www.cnblogs.com/springsource/archive/2012/08/30/2664050.html


    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object getInstance() {

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理类被执行！");
                return method.invoke(target, args);
            }
        });
    }
}
