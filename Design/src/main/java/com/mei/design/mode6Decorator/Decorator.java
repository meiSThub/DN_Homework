package com.mei.design.mode6Decorator;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:所有装饰类的父类
 */

public class Decorator implements Person {

    private Person mPerson;

    public Decorator(Person person) {
        mPerson = person;
    }

    /**
     * 子类需要重写此方法达到具体的装饰目的
     */
    @Override
    public void show() {
        mPerson.show();
        //由子类实现具体的装饰算法
    }
}
