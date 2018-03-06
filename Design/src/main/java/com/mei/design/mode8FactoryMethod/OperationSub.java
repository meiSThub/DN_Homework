package com.mei.design.mode8FactoryMethod;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:减法运算
 */

public class OperationSub implements Operation {
    @Override
    public float calResult(float num1, float num2) {
        return num1 - num2;
    }
}
