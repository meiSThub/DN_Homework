package com.mei.design.mode1SimpleFactory;

/**
 * Created by ubt on 2018/1/8.
 *
 * @description:加法运算实现类
 */

public class OperationAdd extends Operation {

    @Override
    public float operate() {
        return number1 + number2;
    }
}
