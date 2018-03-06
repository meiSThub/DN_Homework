package com.mei.design.mode1SimpleFactory;

/**
 * Created by ubt on 2018/1/8.
 *
 * @description:创建具体计算对象的简单工厂模式
 */

public class OperationFactory {

    public static Operation createOperation(String operate) {
        Operation operation = null;
        switch (operate) {
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
            case "/":
                operation = new OperationDiv();
                break;
        }

        return operation;
    }
}
