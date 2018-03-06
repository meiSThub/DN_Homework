package com.mei.design.mode6Decorator;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:被装饰类的第二个具体实现类，如女人
 */

public class Woman implements Person {

    @Override
    public void show() {
        System.out.println("女生穿什么：");
    }
}
