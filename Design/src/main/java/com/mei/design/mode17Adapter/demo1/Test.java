package com.mei.design.mode17Adapter.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:适配器模式测试用例
 */

public class Test {

    public static void main(String[] args) {
        HonorPhone honorPhone = new HonorPhone();

        // 在中国，用两孔插座充电
        TwoPinSoket twoPinSoket = new TwoPinSoketChina();
        honorPhone.setTwoPinSoket(twoPinSoket);
        honorPhone.charge();

        // 然后坐飞机去美国旅游，美国某旅馆的墙上有只有一个三孔插座
        ThreePinSoket threePinSoket = new ThreePinSoketAmerica();
        testThreePin(threePinSoket);

        // 幸好我有美国适配器，一头插到三孔插座，另一头转换成二孔插座，就可以给我的荣耀手机充电
        AmericaAdapter americaAdapter = new AmericaAdapter(threePinSoket);
        testTwoPin(americaAdapter);

        // 在美国，通过美国适配器，用三空插座充电
        honorPhone.setTwoPinSoket(americaAdapter);
        honorPhone.charge();
    }

    static void testTwoPin(TwoPinSoket twoPinSoket) {
        twoPinSoket.chargeWithTwoPin();
        System.out.println("电压是" + twoPinSoket.voltage() + "伏特\n");
    }

    static void testThreePin(ThreePinSoket threePinSoket) {
        threePinSoket.chargeWithThreePin();
        System.out.println("电压是" + threePinSoket.voltage() + "伏特\n");
    }
}
