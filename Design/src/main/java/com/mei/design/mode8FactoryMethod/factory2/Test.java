package com.mei.design.mode8FactoryMethod.factory2;

/**
 * @author mxb
 * @date 2022/1/5
 * @desc
 * @desired
 */
public class Test {

    public static void main(String[] args) {
        // PhoneFactory factory=new XiaomiFactory();
        PhoneFactory factory=new AppleFactory();
        Phone phone = factory.createPhone();
        phone.call();
    }
}
