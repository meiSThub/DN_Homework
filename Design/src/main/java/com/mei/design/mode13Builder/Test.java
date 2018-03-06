package com.mei.design.mode13Builder;

/**
 * Created by ubt on 2018/1/12.
 *
 * @description:组装者模式测试用例
 */

public class Test {

    public static void main(String[] args) {
        Builder builder = new CarBuilder();
        Car car = CarDirector.assembleProduct(builder);
        System.out.println("car=" + car);
    }
}
