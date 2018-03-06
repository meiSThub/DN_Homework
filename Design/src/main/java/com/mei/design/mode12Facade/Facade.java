package com.mei.design.mode12Facade;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:外观类
 */

public class Facade {

    private ChildSystem1 mChildSystem1;
    private ChildSystem2 mChildSystem2;
    private ChildSystem3 mChildSystem3;

    public Facade() {
        mChildSystem1 = new ChildSystem1();
        mChildSystem2 = new ChildSystem2();
        mChildSystem3 = new ChildSystem3();
    }

    /**
     * 外观类向外界暴露的操作子系统的方法
     */
    public void invokeChildSystem1() {
        mChildSystem1.testChild1();
    }

    public void invokeChildSystem2() {
        mChildSystem2.testChild2();
    }

    public void invokeChildSystem3() {
        mChildSystem3.testChild3();
    }
}
