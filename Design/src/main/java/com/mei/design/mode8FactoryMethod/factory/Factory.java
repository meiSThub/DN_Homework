package com.mei.design.mode8FactoryMethod.factory;


import com.mei.design.mode8FactoryMethod.Operation;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:工厂方法接口，所有的工厂类均实现此接口
 */

public interface Factory {
    Operation createInstance();
}
