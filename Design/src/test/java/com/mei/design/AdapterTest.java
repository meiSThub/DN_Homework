package com.mei.design;

import com.mei.design.mode17Adapter.demo1.AmericaAdapter;
import com.mei.design.mode17Adapter.demo1.HonorPhone;
import com.mei.design.mode17Adapter.demo1.ThreePinSoket;
import com.mei.design.mode17Adapter.demo1.ThreePinSoketAmerica;
import com.mei.design.mode17Adapter.demo1.TwoPinSoket;
import com.mei.design.mode17Adapter.demo1.TwoPinSoketChina;
import com.mei.design.mode17Adapter.demo2.AmericaPlayer;
import com.mei.design.mode17Adapter.demo2.ForeignPlayer;
import com.mei.design.mode17Adapter.demo2.Player;
import com.mei.design.mode17Adapter.demo2.TranslatorAdapter;

import org.junit.Test;



/**
 * Created by ubt on 2018/1/17.
 *
 * @description:
 */

public class AdapterTest {

    @Test
    public void test() {
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

    void testTwoPin(TwoPinSoket twoPinSoket) {
        twoPinSoket.chargeWithTwoPin();
        System.out.println("电压是" + twoPinSoket.voltage() + "伏特\n");
    }

    void testThreePin(ThreePinSoket threePinSoket) {
        threePinSoket.chargeWithThreePin();
        System.out.println("电压是" + threePinSoket.voltage() + "伏特\n");
    }

    @Test
    public void test2() {
        Player americaPlayer = new AmericaPlayer();
        americaPlayer.setName("科比");
        americaPlayer.attack();

        ForeignPlayer foreignPlayer = new ForeignPlayer();
        foreignPlayer.setName("姚明");

        TranslatorAdapter adapter = new TranslatorAdapter(foreignPlayer);
        adapter.attack();

    }
}
