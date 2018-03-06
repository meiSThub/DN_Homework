package com.mei.design.mode7Proxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:动态代理测试类
 */

public class Test {


    public static void main(String[] args) {
        //真实角色
        Star star = new Star();
        //代理角色处理器
        StarHander hander = new StarHander(star);

        IStar proxy = (IStar) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{IStar.class}, hander);
        proxy.sing();
    }
}
