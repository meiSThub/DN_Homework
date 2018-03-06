package com.mei.design.mode17Adapter.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: adaptee(被适配者) ———— 假设在美国某旅馆的墙上，只有一个三孔插座
 */

public interface ThreePinSoket {

    /**
     * 用三孔插座充电
     */
    void chargeWithThreePin();

    /**
     * 电压
     *
     * @return
     */
    int voltage();
}
