package com.mei.design.mode12Facade;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:
 */

public class Test {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.invokeChildSystem1();
        facade.invokeChildSystem2();
        facade.invokeChildSystem3();
    }
}
