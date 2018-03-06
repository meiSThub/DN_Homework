package com.mei.design.mode17Adapter.demo1;

/**
 * Created by ubt on 2018/1/17.
 * 适配器类
 *
 * @description: 去美国旅游，必须带上一个“美国适配器”：实现两孔插座，
 * 组合三孔插座。用来给我的荣耀手机充电
 */

public class AmericaAdapter implements TwoPinSoket {

    ThreePinSoket mThreePinSoket;//被适配者

    public AmericaAdapter(ThreePinSoket threePinSoket) {
        mThreePinSoket = threePinSoket;
    }

    @Override
    public void chargeWithTwoPin() {
        mThreePinSoket.chargeWithThreePin();
    }

    /**
     * threePinSoket.voltage() * 2; // 适配器把电压从 110V 升到 220V
     *
     * @return
     */
    @Override
    public int voltage() {
        return mThreePinSoket.voltage() * 2;// 适配器把电压从 110V 升到 220V
    }
}
