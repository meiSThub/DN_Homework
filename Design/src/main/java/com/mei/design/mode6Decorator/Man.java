package com.mei.design.mode6Decorator;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:被装饰类的具体实现类，如这里的男人
 */

public class Man implements Person {

    @Override
    public void show() {
        System.out.println("男人穿什么：");
    }
}
