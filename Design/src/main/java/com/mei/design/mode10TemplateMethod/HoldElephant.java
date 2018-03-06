package com.mei.design.mode10TemplateMethod;

/**
 * Created by ubt on 2018/1/11.
 * 把大象装冰箱，需要几步
 *
 * @description:模板抽象类
 */

public abstract class HoldElephant {

    /**
     * 把大象装冰箱
     */
    public void hold() {
        openRefrigeratorDoor();
        holdElephantToRefrigerator();
        closeRefrigeratorDoor();
    }

    /**
     * 打开冰箱门
     */
    protected abstract void openRefrigeratorDoor();

    /**
     * 把大象装冰箱
     */
    protected abstract void holdElephantToRefrigerator();

    /**
     * 关闭冰箱门
     */
    protected abstract void closeRefrigeratorDoor();
}
