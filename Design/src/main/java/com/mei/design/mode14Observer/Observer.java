package com.mei.design.mode14Observer;

/**
 * Created by ubt on 2018/1/15.
 *
 * @description:观察者接口
 */

public interface Observer {
    void update();//被观察者有变化时，通过此方法通知观察者
}
