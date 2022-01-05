package com.mei.design.mode15AbstractFactory.factory2;



/**
 * @author mxb
 * @date 2022/1/5
 * @desc
 * @desired
 */
public class Test {

    public static void main(String[] args) {
        // PhoneFactory factory=new XiaomiFactory();
        ProductFactory factory = new AppleFactory();
        Phone phone = factory.createPhone();
        phone.call();
        Computer computer = factory.createComputer();
        computer.internet();
    }
}
