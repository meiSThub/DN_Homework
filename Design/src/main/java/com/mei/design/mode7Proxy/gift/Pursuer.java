package com.mei.design.mode7Proxy.gift;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:美女的追求者,被代理者
 */

public class Pursuer implements IGiveGift {


    public Pursuer() {

    }

    @Override
    public void giveDolls() {
        System.out.println("给美女送洋娃娃");
    }

    @Override
    public void giveFlowers() {
        System.out.println("给美女送鲜花");
    }
}
