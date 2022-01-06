package com.mei.design.mode7Proxy.star;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:
 */

public class Test {

    public static void main(String[] args) {
        Star star = new Star();
        StarProxy proxy = new StarProxy(star);
        proxy.accept("sing1");
    }
}
