package com.mei.design.mode16State.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: 电梯具体状态实现类：停止状态
 */

public class StoppingState extends LiftState {

    public StoppingState(Lift liftContext) {
        super(liftContext);
    }

    /**
     * 在电梯停止的时候，电梯门肯定是可以打开的
     * 从停止状态切换到开门状态
     */
    @Override
    public void open() {
        setState(mLiftContext.getOpenningState());
        getCurrState().open();
    }

    /**
     * 电梯在停止状态时，电梯门就是关闭的
     */
    @Override
    public void close() {
        //do nothing
    }

    /**
     * 电梯停止状态时，重新跑起来
     * 从停止状态切换到运行状态
     */
    @Override
    public void run() {
        setState(mLiftContext.getRunningState());
        getCurrState().run();
    }

    /**
     * 停止状态，电梯就要执行停止运行的操作，即停止操作具体实现
     */
    @Override
    public void stop() {
        System.out.println("电梯停止了...");
    }
}
