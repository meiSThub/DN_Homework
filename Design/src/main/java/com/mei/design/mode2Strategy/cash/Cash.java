package com.mei.design.mode2Strategy.cash;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:收银类
 */

public class Cash {

    protected CashStrategy mCashStrategy;

    public Cash(String strategyType) {
        //实例化对象这相当于是简单工厂模式
        switch (strategyType) {
            case "正常收费":
                mCashStrategy = new NormalCashStrategy();
                break;
            case "满300返100":
                mCashStrategy = new ReturnCashStrategy(300, 100);
                break;
            case "打8折":
                mCashStrategy = new RebateCashStrategy(0.8f);
                break;
        }
    }

    public float calResult(float money) {
        if (mCashStrategy != null) {
            return mCashStrategy.calPrice(money);
        }
        return 0;
    }

}
