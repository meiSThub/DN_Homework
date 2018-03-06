package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:工作状态抽象接口
 */

public abstract class WorkState {

    protected Work mWork;

    public WorkState(Work work) {
        mWork = work;
    }

    /**
     * 定义一个写程序的抽象方法
     */
    public abstract void writeProgram();
}
