package com.mei.design.mode16State.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:电梯类
 */

public class Lift {

    private LiftState mCurrState;
    ////定义出所有的电梯状态
    private LiftState mOpenningState;//开门状态
    private LiftState mClosingState;//关门状态
    private LiftState mRunningState;//运行状态
    private LiftState mStoppingState;//停止状态

    public Lift() {
        mOpenningState = new OpenningState(this);
        mClosingState = new ClosingState(this);
        mRunningState = new RunningState(this);
        mStoppingState = new StoppingState(this);
    }

    public void open() {
        mOpenningState.open();
    }

    public void close() {
        mClosingState.close();
    }

    public void run() {
        mRunningState.run();
    }

    public void stop() {
        mStoppingState.stop();
    }

    public void setCurrState(LiftState state) {
        mCurrState = state;
    }

    public LiftState getCurrState() {
        return mCurrState;
    }

    public LiftState getOpenningState() {
        return mOpenningState;
    }

    public LiftState getClosingState() {
        return mClosingState;
    }

    public LiftState getRunningState() {
        return mRunningState;
    }

    public LiftState getStoppingState() {
        return mStoppingState;
    }
}
