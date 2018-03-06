package com.mei.design;

import com.mei.design.mode8FactoryMethod.Operation;
import com.mei.design.mode8FactoryMethod.factory.Factory;
import com.mei.design.mode8FactoryMethod.factory.FactoryAdd;

import org.junit.Test;



/**
 * Created by ubt on 2018/1/11.
 *
 * @description:工厂方法测试用例
 */

public class FactoryMethodTest {

    @Test
    public void test() {
        Factory factoryAdd = new FactoryAdd();
        Operation operationAdd = factoryAdd.createInstance();
        float result = operationAdd.calResult(1, 2);
        System.out.println("运算结果：" + result);

        result = operationAdd.calResult(2, 5);
        System.out.println("运算结果：" + result);

    }
}
