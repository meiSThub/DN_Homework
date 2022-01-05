package com.mei.design.mode15AbstractFactory.factory2;


/**
 * @author mxb
 * @date 2022/1/5
 * @desc
 * @desired
 */
public class XiaomiFactory implements ProductFactory {

    @Override
    public Phone createPhone() {
        return new XiaomiPhone();
    }

    @Override
    public Computer createComputer() {
        return new XiaomiComputer();
    }
}
