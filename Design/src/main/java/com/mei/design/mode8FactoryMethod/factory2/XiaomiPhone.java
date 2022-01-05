package com.mei.design.mode8FactoryMethod.factory2;

/**
 * @author mxb
 * @date 2022/1/5
 * @desc
 * @desired
 */
public class XiaomiPhone implements Phone {

    @Override
    public void call() {
        System.out.println("小米手机：打电话");
    }
}
