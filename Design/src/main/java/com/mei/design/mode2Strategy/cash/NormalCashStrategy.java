package com.mei.design.mode2Strategy.cash;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:正常收费策略，不打折，不返现
 */

public class NormalCashStrategy implements CashStrategy {

    @Override
    public float calPrice(float money) {
        return money;
    }
}
