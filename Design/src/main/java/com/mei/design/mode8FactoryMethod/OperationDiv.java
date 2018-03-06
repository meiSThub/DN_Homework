package com.mei.design.mode8FactoryMethod;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:出发运算
 */

public class OperationDiv implements Operation {
    @Override
    public float calResult(float num1, float num2) {
        if (num2 == 0) {
            return 0;
        }
        return num1 / num2;
    }
}
