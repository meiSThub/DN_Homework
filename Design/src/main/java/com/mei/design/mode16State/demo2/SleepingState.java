package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:晚间工作状态
 */

public class SleepingState extends WorkState {

    public SleepingState(Work work) {
        super(work);
    }

    @Override
    public void writeProgram() {
        System.out.println("当前时间:" + mWork.getHour() + "点不行了，睡着了.");
    }
}
