package com.mei.design.mode8FactoryMethod.factory;


import com.mei.design.mode8FactoryMethod.Operation;
import com.mei.design.mode8FactoryMethod.OperationSub;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:减法算数器的工厂类
 */

public class FactorySub implements Factory {
    @Override
    public Operation createInstance() {
        return new OperationSub();
    }
}
