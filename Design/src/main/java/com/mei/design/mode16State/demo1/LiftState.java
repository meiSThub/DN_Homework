package com.mei.design.mode16State.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:电梯状态抽象类,电梯状态有：开门，关门，运行，停止
 */

public abstract class LiftState {

    public Lift mLiftContext;

    public LiftState(Lift liftContext) {
        mLiftContext = liftContext;
    }

    /**
     * 设置电梯当前的状态
     *
     * @param state
     */
    protected void setState(LiftState state) {
        if (mLiftContext != null) {
            mLiftContext.setCurrState(state);
        }
    }

    /**
     * 获取电梯当前的状态
     *
     * @return
     */
    protected LiftState getCurrState() {
        return mLiftContext.getCurrState();
    }

    /**
     * 电梯门打开
     */
    public abstract void open();

    /**
     * 电梯关门
     */
    public abstract void close();

    /**
     * 电梯运行
     */
    public abstract void run();

    /**
     * 电梯停止
     */
    public abstract void stop();
}
