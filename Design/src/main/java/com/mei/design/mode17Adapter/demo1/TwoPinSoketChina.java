package com.mei.design.mode17Adapter.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: client(具体的Target) ———— 这个就是我在中国的墙上的两个插孔的插座，我充电只能用这个
 */

public class TwoPinSoketChina implements TwoPinSoket {

    @Override
    public void chargeWithTwoPin() {
        System.out.println("中国标准的两孔的插座");
    }

    /**
     * 中国
     *
     * @return
     */
    @Override
    public int voltage() {
        return 220;
    }
}
