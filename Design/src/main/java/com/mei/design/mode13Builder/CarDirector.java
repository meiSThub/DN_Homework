package com.mei.design.mode13Builder;

/**
 * Created by ubt on 2018/1/12.
 *
 * @description:构造汽车指导类,即汽车最终的组装者。
 */

public class CarDirector {

    public static Car assembleProduct(Builder builder) {
        builder.makeControl();
        builder.makeEngine();
        builder.makeWheel();
        builder.makeOthers();
        return builder.getCar();
    }

}
