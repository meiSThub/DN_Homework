package com.mei.design.mode1SimpleFactory;

/**
 * Created by ubt on 2018/1/8.
 *
 * @description:减法运算实现类
 */

public class OperationSub extends Operation {
    @Override
    public float operate() {
        return number1 - number2;
    }
}
