package com.mei.design.mode16State.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:电梯具体状态实现类：运行状态
 */

public class RunningState extends LiftState {

    public RunningState(Lift liftContext) {
        super(liftContext);
    }

    /**
     * 电梯在运行状态的时候，电梯门肯定是不能打开的
     */
    @Override
    public void open() {
        //do nothing
    }

    /**
     * 电梯在运行状态下，电梯门肯定是已经关闭了的，否则无法运行
     */
    @Override
    public void close() {
        //do nothing
    }

    /**
     * 运行状态下，电梯就要执行上下跑的操作，即上下跑操作具体实现
     */
    @Override
    public void run() {
        System.out.println("电梯上下跑...");
    }

    /**
     * 电梯在运行状态下，肯定是可以执行停止操作的
     * 切换电梯的状态为停止运行状态，并执行停止操作
     */
    @Override
    public void stop() {
        setState(mLiftContext.getStoppingState());
        getCurrState().stop();
    }
}
