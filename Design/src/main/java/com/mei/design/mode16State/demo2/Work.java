package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:工作类
 */

public class Work {

    private int hour;//当前时间
    private boolean mIsFinishWork = false;
    private WorkState mForenoonState;//上午工作状态
    private WorkState mNoonState;//中午工作状态
    private WorkState mAfternoonState;//下午工作状态
    private WorkState mEveningState;//晚上工作状态
    private WorkState mSleepingState;//睡眠状态
    private WorkState mRestState;//下班休息状态
    private WorkState mCurrState;

    public Work() {
        mForenoonState = new ForenoonState(this);
        mNoonState = new NoonState(this);
        mAfternoonState = new AfternoonState(this);
        mEveningState = new EveningState(this);
        mSleepingState = new SleepingState(this);
        mRestState = new RestState(this);
        //初始状态
        mCurrState = mForenoonState;
    }

    public void writeProgram() {
        if (mCurrState != null) {
            mCurrState.writeProgram();
        }
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public boolean isFinishWork() {
        return mIsFinishWork;
    }

    public WorkState getCurrState() {
        return mCurrState;
    }

    public void setCurrState(WorkState currState) {
        mCurrState = currState;
    }

    public void setFinishWork(boolean finishWork) {
        mIsFinishWork = finishWork;
    }

    public WorkState getForenoonState() {
        return mForenoonState;
    }

    public WorkState getNoonState() {
        return mNoonState;
    }

    public WorkState getAfternoonState() {
        return mAfternoonState;
    }

    public WorkState getEveningState() {
        return mEveningState;
    }

    public WorkState getSleepingState() {
        return mSleepingState;
    }

    public WorkState getRestState() {
        return mRestState;
    }
}
