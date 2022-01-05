package com.mei.design.mode15AbstractFactory.factory2;


/**
 * @author mxb
 * @date 2022/1/5
 * @desc
 * @desired
 */
public class AppleFactory implements ProductFactory {

    @Override
    public Phone createPhone() {
        return new ApplePhone();
    }

    @Override
    public Computer createComputer() {
        return new AppleComputer();
    }
}
