package com.mei.design.mode7Proxy.gift;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:代理类，帮忙送礼物的人
 */

public class Proxy implements IGiveGift {

    private Pursuer mPursuer;//被代理对象
    private BeautifulGirl mGirl;

    public Proxy(Pursuer pursuer) {
        mPursuer = pursuer;
    }

    @Override
    public void giveDolls() {
        mPursuer.giveDolls();
    }

    @Override
    public void giveFlowers() {
        mPursuer.giveFlowers();
    }
}
