package com.mei.design.mode16State.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: 电梯具体状态实现类：关门状态
 */

public class ClosingState extends LiftState {

    public ClosingState(Lift liftContext) {
        super(liftContext);
    }

    /**
     * 电梯门关闭状态下，电梯还是可以执行开门动作的
     */
    @Override
    public void open() {
        setState(mLiftContext.getOpenningState());
        getCurrState().open();
    }

    /**
     * 关闭状态下，电梯就要执行关闭的操作，即关闭操作具体实现
     */
    @Override
    public void close() {
        System.out.println("电梯门关闭...");
    }

    /**
     * 电梯门关闭状态下，电梯可以运行了
     */
    @Override
    public void run() {
        setState(mLiftContext.getRunningState());
        getCurrState().run();
    }

    /**
     * 在电梯门关闭状态下，电梯在没有按楼层的情况下，
     * 电梯是不运行的，即也可以是停止状态
     */
    @Override
    public void stop() {
        setState(mLiftContext.getStoppingState());
        getCurrState().stop();
    }
}
