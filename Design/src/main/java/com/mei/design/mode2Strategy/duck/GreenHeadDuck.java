package com.mei.design.mode2Strategy.duck;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:假设绿头鸭子会飞
 */

public class GreenHeadDuck extends Duck {

    public GreenHeadDuck() {
        mFlyableStrategy = new FlyableYesStrategy();
    }

}
