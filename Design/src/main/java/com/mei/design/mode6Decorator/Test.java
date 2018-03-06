package com.mei.design.mode6Decorator;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:测试用例
 */

public class Test {

    public static void main(String[] args) {
        Person man = new Man();
        man.show();

        man = new CoatDecorator(man, "夹克");
        man = new PantsDecorator(man, "牛仔裤");
        man = new ShoesDecorator(man, "球鞋");
        man.show();

        Person woman = new Woman();
        woman = new CoatDecorator(woman, "裙子");
        woman = new PantsDecorator(woman, "打底裤");
        woman = new ShoesDecorator(woman, "高跟鞋");
        woman.show();
    }
}
