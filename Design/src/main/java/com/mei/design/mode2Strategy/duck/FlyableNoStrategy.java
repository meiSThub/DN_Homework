package com.mei.design.mode2Strategy.duck;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:鸭子不会飞的策略算法具体实现
 */

public class FlyableNoStrategy implements FlyableStrategy {
    @Override
    public void fly() {
        System.out.println("鸭子不会飞");
    }
}
