package com.mei.design.mode2Strategy.cash;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:打折策略具体实现
 */

public class RebateCashStrategy implements CashStrategy {

    private float mRebate;//折扣

    public RebateCashStrategy(float rebate) {
        mRebate = rebate;
    }

    @Override
    public float calPrice(float money) {
        return money * mRebate;
    }
}
