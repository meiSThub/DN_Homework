package com.mei.design.mode8FactoryMethod.factory2;

/**
 * @author mxb
 * @date 2022/1/5
 * @desc
 * @desired
 */
public class AppleFactory implements PhoneFactory {

    @Override
    public Phone createPhone() {
        return new ApplePhone();
    }
}
