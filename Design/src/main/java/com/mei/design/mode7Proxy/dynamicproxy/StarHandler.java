package com.mei.design.mode7Proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:代理角色的处理器
 */

public class StarHandler implements InvocationHandler {


    private Star mStar;

    //通过构造器来初始化真实角色
    public StarHandler(Star star) {
        mStar = star;
    }

    /**
     * 所有流程控制都在invoke方法中
     *
     * @param proxy  代理类
     * @param method 正在调用的方法
     * @param args   方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;
        System.out.println("真实角色调用之前的处理。。。。");
        if ("accept".equals(method.getName())) {
            object = method.invoke(mStar, args);
        }
        System.out.println("真实角色调用之后的处理.....");
        return object;
    }
}
