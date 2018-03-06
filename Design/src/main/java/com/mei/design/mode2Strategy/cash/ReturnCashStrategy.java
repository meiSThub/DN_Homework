package com.mei.design.mode2Strategy.cash;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:满多少钱返现 策略
 */

public class ReturnCashStrategy implements CashStrategy {

    private float fullPrice;//满足多少钱的临界值
    private float returnPrice;//返现

    public ReturnCashStrategy(float fullPrice, float returnPrice) {
        this.fullPrice = fullPrice;
        this.returnPrice = returnPrice;
    }

    @Override
    public float calPrice(float money) {
        if (money > fullPrice) {
            return money - (money / fullPrice) * returnPrice;
        }
        return 0;
    }
}
