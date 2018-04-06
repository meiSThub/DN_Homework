package com.mei.design.mode8FactoryMethod.factory;


import com.mei.design.mode8FactoryMethod.Operation;
import com.mei.design.mode8FactoryMethod.OperationDiv;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:出发算数器的工厂类
 */

public class FactoryDiv implements Factory {
    @Override
    public Operation createInstance() {
        return new OperationDiv();
    }
}
