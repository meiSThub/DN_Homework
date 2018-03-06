package com.mei.design.mode9Prototype;

/**
 * Created by ubt on 2018/1/11.
 * <p>
 * 抽象原型类Prototype类需要具备以下两个条件
 * 1、实现Cloneable接口
 * 2、重写Object类中的clone方法
 *
 * @description:
 */

public class Prototype implements Cloneable {

    @Override
    protected Object clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}
