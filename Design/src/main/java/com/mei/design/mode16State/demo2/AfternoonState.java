package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:下午工作状态
 */

public class AfternoonState extends WorkState {

    public AfternoonState(Work work) {
        super(work);
    }

    @Override
    public void writeProgram() {
        if (mWork.getHour() < 17) {
            System.out.println("当前时间:" + mWork.getHour() + "点 下午状态还不错，继续努力");
        } else {
            mWork.setCurrState(mWork.getEveningState());
            mWork.writeProgram();
        }
    }
}
