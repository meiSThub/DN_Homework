package com.mei.design.mode8FactoryMethod.factory;

import ubt.com.designmode.mode8FactoryMethod.Operation;
import ubt.com.designmode.mode8FactoryMethod.OperationMul;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:乘法算数器的工厂类
 */

public class FactoryMul implements Factory {
    @Override
    public Operation createInstance() {
        return new OperationMul();
    }
}
