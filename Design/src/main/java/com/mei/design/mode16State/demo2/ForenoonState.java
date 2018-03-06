package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:上午工作状态
 */

public class ForenoonState extends WorkState {

    public ForenoonState(Work work) {
        super(work);
    }

    @Override
    public void writeProgram() {
        if (mWork.getHour() < 12) {
            System.out.println("当前时间:" + mWork.getHour() + "点 上午工作，精神百倍");
        } else {
            mWork.setCurrState(mWork.getNoonState());
            mWork.writeProgram();
        }
    }
}
