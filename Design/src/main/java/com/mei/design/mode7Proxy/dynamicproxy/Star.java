package com.mei.design.mode7Proxy.dynamicproxy;

/**
 * Created by ubt on 2018/1/11.
 * 真实角色实现类：这里的真实角色中其实只做了一个唱歌的操作，这是真实角色真正的业务逻辑部分
 *
 * @description:
 */

public class Star implements IStar {

    @Override
    public void sing() {
        System.out.println("张学友.sing()");//真实角色的操作：真正的业务逻辑
    }

    @Override
    public void accept() {
        System.out.println("Star.accept()");
    }
}
