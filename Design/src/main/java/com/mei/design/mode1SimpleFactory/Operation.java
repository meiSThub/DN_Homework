package com.mei.design.mode1SimpleFactory;

/**
 * Created by ubt on 2018/1/8.
 *
 * @description:运算抽象类
 */

public abstract class Operation {

    protected float number1;
    protected float number2;//操作数2

    public Operation() {

    }

    public Operation(float number1, float number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public float getNumber1() {
        return number1;
    }

    public void setNumber1(float number1) {
        this.number1 = number1;
    }

    public float getNumber2() {
        return number2;
    }

    public void setNumber2(float number2) {
        this.number2 = number2;
    }

    /**
     * 具体计算算法，由子类实现
     *
     * @return
     */
    public abstract float operate();
}
