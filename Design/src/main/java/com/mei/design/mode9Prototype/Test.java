package com.mei.design.mode9Prototype;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:原型模式测试用例
 */

public class Test {

    public static void main(String[] args) {
        ConcretePrototype cp = new ConcretePrototype();
        for (int i = 0; i < 10; i++) {
            ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
            clonecp.show();
        }
    }
}
