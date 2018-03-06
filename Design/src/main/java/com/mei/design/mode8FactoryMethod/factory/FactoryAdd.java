package com.mei.design.mode8FactoryMethod.factory;

import ubt.com.designmode.mode8FactoryMethod.Operation;
import ubt.com.designmode.mode8FactoryMethod.OperationAdd;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:加法算数器的工厂类
 */

public class FactoryAdd implements Factory {

    @Override
    public Operation createInstance() {
        return new OperationAdd();
    }
}
