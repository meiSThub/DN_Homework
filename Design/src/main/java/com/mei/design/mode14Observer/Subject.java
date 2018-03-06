package com.mei.design.mode14Observer;

/**
 * Created by ubt on 2018/1/15.
 *
 * @description:主题（发布者，被观察者）
 */

public interface Subject {
    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     *
     * @param observer 观察者
     */
    void removeObserver(Observer observer);

    /**
     * 当被观察者有变化时通知观察者
     */
    void notifyObservers();
}
