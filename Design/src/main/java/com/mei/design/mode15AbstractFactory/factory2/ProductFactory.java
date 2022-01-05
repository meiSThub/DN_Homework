package com.mei.design.mode15AbstractFactory.factory2;


/**
 * @author mxb
 * @date 2022/1/5
 * @desc
 * @desired
 */
public interface ProductFactory {

    /** 生产手机 */
    Phone createPhone();

    /** 生产电脑 */
    Computer createComputer();
}
