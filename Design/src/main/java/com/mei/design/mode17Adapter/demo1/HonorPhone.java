package com.mei.design.mode17Adapter.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:荣耀手机类，Client
 */

public class HonorPhone {

    private TwoPinSoket mTwoPinSoket;

    public void setTwoPinSoket(TwoPinSoket twoPinSoket) {
        mTwoPinSoket = twoPinSoket;
    }

    /**
     * 给手机充电
     */
    public void charge() {
        System.out.println("华为荣耀手机， " + mTwoPinSoket.voltage() + " 伏特充电中\n");
    }
}
