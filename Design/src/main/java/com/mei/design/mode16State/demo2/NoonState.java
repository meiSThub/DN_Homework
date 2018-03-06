package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:中午工作状态
 */

public class NoonState extends WorkState {

    public NoonState(Work work) {
        super(work);
    }

    @Override
    public void writeProgram() {
        if (mWork.getHour() < 13) {
            System.out.println("当前时间:" + mWork.getHour() + "点 饿了，午饭；犯困，午休");
        } else {
            mWork.setCurrState(mWork.getAfternoonState());
            mWork.writeProgram();
        }
    }
}
