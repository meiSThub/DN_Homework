package com.mei.design.mode8FactoryMethod;


import com.mei.design.mode8FactoryMethod.factory.Factory;
import com.mei.design.mode8FactoryMethod.factory.FactoryAdd;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:
 */

public class Test {

    public static void main(String[] args) {
        Factory factoryAdd = new FactoryAdd();
        Operation operationAdd = factoryAdd.createInstance();
        operationAdd.calResult(1, 2);

        operationAdd.calResult(2, 5);

    }
}
