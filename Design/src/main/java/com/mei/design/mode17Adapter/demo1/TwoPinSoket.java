package com.mei.design.mode17Adapter.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: target(适配目标) ———— 我的荣耀手机充电器是两个插头，所以需要两个插孔的插座
 */

public interface TwoPinSoket {

    /**
     * 用两孔插座充电
     */
    void chargeWithTwoPin();


    /**
     * 电压
     *
     * @return
     */
    int voltage();
}
