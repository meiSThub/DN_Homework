package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:晚间工作状态
 */

public class EveningState extends WorkState {

    public EveningState(Work work) {
        super(work);
    }

    @Override
    public void writeProgram() {
        if (mWork.isFinishWork()) {
            mWork.setCurrState(mWork.getRestState());
            mWork.writeProgram();
        } else {
            if (mWork.getHour() < 21) {
                System.out.println("当前时间:" + mWork.getHour() + "点 加班哦，疲惫至极");
            } else {
                mWork.setCurrState(mWork.getSleepingState());
                mWork.writeProgram();
            }
        }
    }
}
