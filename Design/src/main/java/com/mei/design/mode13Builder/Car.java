package com.mei.design.mode13Builder;

/**
 * Created by ubt on 2018/1/12.
 *
 * @description:汽车类
 */

public class Car {
    private String wheel;
    private String control;
    private String engine;
    private String others;

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "Car{" +
                "wheel='" + wheel + '\'' +
                ", control='" + control + '\'' +
                ", engine='" + engine + '\'' +
                ", others='" + others + '\'' +
                '}';
    }
}
