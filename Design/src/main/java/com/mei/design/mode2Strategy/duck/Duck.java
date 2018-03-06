package com.mei.design.mode2Strategy.duck;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:鸭子超类，所有的鸭子都继承与该类。
 */

public class Duck {

    public static final int FLY_TYPE_YES = 0;//策略类型：鸭子会飞
    public static final int FLY_TYPE_NO = 1;//策略类型：鸭子不会飞

    protected FlyableStrategy mFlyableStrategy;

    public Duck() {
    }

    public Duck(int flyableType) {
        setFlyableStrategy(flyableType);
    }

    /**
     * 设置飞翔策略
     *
     * @param strategyType 策略类型
     */
    public void setFlyableStrategy(int strategyType) {
        switch (strategyType) {
            case FLY_TYPE_YES:
                mFlyableStrategy = new FlyableYesStrategy();
                break;
            case FLY_TYPE_NO:
                mFlyableStrategy = new FlyableNoStrategy();
                break;
        }
    }

    public void setFlyableStrategy(FlyableStrategy flyableStrategy) {
        mFlyableStrategy = flyableStrategy;
    }

    /**
     * 鸭子飞的算法，由具体的策略实现
     */
    public void fly() {
        if (mFlyableStrategy != null) {
            mFlyableStrategy.fly();
        }
    }


    /*定义的公共方法*/

    /**
     * 鸭子会游泳
     */
    public void swim() {
        System.out.println("鸭子会游泳");
    }
}
