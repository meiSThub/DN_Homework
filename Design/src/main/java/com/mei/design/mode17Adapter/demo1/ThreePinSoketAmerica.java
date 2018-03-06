package com.mei.design.mode17Adapter.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:实现一个具体的Adaptee,即美国的三孔插座
 */

public class ThreePinSoketAmerica implements ThreePinSoket {

    @Override
    public void chargeWithThreePin() {
        System.out.println("美国标准的三孔的插座");
    }

    /**
     * 美国电压是110伏
     *
     * @return
     */
    @Override
    public int voltage() {
        return 110;
    }
}
