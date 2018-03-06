package com.mei.design.mode13Builder;


/**
 * Created by ubt on 2018/1/12.
 *
 * @description:实现了Builder接口的具体建造者类，即汽车部件的具体生产者
 */

public class CarBuilder implements Builder {

    private Car mCar;

    public CarBuilder() {
        mCar = new Car();
    }

    @Override
    public void makeWheel() {
        mCar.setWheel("车轮");
        System.out.println("生产车轮");
    }

    @Override
    public void makeControl() {
        mCar.setControl("方向盘");
        System.out.println("生产方向盘");
    }

    @Override
    public void makeEngine() {
        mCar.setEngine("发动机");
        System.out.println("生产发动机");
    }

    @Override
    public void makeOthers() {
        mCar.setOthers("其他部件");
        System.out.println("生产其他部件");
    }

    @Override
    public Car getCar() {
        return mCar;
    }

    /**
     * 自己承担Director组装者的角色
     *
     * @return
     */
    public Car assembleCar() {
        makeControl();
        makeEngine();
        makeWheel();
        makeOthers();
        return getCar();
    }
}
