package com.mei.design;

import com.mei.design.mode1SimpleFactory.Operation;
import com.mei.design.mode1SimpleFactory.OperationFactory;

import org.junit.Test;



/**
 * Created by ubt on 2018/1/8.
 *
 * @description:简单工厂模式测试用例
 */

public class SimpleFactoryTest {

    @Test
    public void test() {
        Operation operation = OperationFactory.createOperation("+");
        operation.setNumber1(1);
        operation.setNumber2(2);

        System.out.println("result=" + operation.operate());
    }
}
