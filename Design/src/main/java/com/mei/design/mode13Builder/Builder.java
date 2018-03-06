package com.mei.design.mode13Builder;

/**
 * Created by ubt on 2018/1/12.
 *
 * @description:指定创建汽车各个部件的方法的抽象接口类 车轮、方向盘、发动机还有各种小零件等等
 */

public interface Builder {
    /**
     * 生产车轮
     */
    void makeWheel();

    /**
     * 生产方向盘
     */
    void makeControl();

    /**
     * 生产发动机
     */
    void makeEngine();

    /**
     * 生产其他所有的小部件
     */
    void makeOthers();

    /**
     * 获取生产的汽车
     *
     * @return
     */
    Car getCar();
}
