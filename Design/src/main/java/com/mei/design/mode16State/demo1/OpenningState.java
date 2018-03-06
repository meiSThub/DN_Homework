package com.mei.design.mode16State.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: 电梯具体状态实现类：开门状态
 * <p>
 * 在电梯门开启的状态下能做什么事情
 * 在电梯门打开的状态下，电梯能过度到的状态是关闭状态，因此我们在Close()中定义了状态变更，
 * 同时把Close 这个动作也委托了给CloseState 类下的Close 方法执行
 */

public class OpenningState extends LiftState {

    public OpenningState(Lift liftContext) {
        super(liftContext);
    }

    /**
     * 打开状态下，电梯就要执行开门的操作，即开门操作具体实现
     */
    @Override
    public void open() {
        System.out.println("电梯门开启");
    }

    /**
     * 在开门状态下，电梯肯定是可以执行关门动作的
     */
    @Override
    public void close() {
        setState(mLiftContext.getClosingState());
        getCurrState().close();
    }

    /**
     * 在开门状态下，电梯肯定不能跑，什么都不做
     */
    @Override
    public void run() {
        //do nothing
    }

    /**
     * 在开门状态下，电梯肯定是停止了的，什么都不做
     */
    @Override
    public void stop() {
        //do nothing
    }
}
