package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:下班休息工作状态
 */

public class RestState extends WorkState {

    public RestState(Work work) {
        super(work);
    }

    @Override
    public void writeProgram() {
        System.out.println("当前时间:" + mWork.getHour() + "点下班回家了");
    }
}
