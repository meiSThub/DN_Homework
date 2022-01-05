package com.mei.design;


import com.mei.design.mode7Proxy.dynamicproxy.IStar;
import com.mei.design.mode7Proxy.dynamicproxy.StarHandler;
import com.mei.design.mode7Proxy.star.Star;
import com.mei.design.mode7Proxy.star.StarProxy;

import org.junit.Test;

import java.lang.reflect.Proxy;


/**
 * Created by ubt on 2018/1/9.
 *
 * @description:
 */

public class ProxyTest {

    @Test
    public void test() {
        Star star = new Star();
        StarProxy proxy = new StarProxy(star);
        proxy.accept();
        proxy.sing();//真实对象的操作


    }

    @Test
    public void dynamicProxyTest() {
        //真实角色
        com.mei.design.mode7Proxy.dynamicproxy.Star star = new com.mei.design.mode7Proxy.dynamicproxy.Star();
        //代理角色处理器
        StarHandler hander = new StarHandler(star);

        com.mei.design.mode7Proxy.dynamicproxy.IStar proxy = (com.mei.design.mode7Proxy.dynamicproxy.IStar)
                Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                        new Class[]{IStar.class}, hander);
        proxy.sing();
    }
}
