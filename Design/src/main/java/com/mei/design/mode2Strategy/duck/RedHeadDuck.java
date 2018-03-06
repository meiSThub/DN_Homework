package com.mei.design.mode2Strategy.duck;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:假设红头鸭子不会飞
 */

public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        mFlyableStrategy = new FlyableNoStrategy();
    }

}
