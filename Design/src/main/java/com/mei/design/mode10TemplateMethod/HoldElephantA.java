package com.mei.design.mode10TemplateMethod;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:模板抽象类的实现类，实现把大象装冰箱的具体步骤
 */

public class HoldElephantA extends HoldElephant {

    @Override
    protected void openRefrigeratorDoor() {
        System.out.println("打开冰箱门");
    }

    @Override
    protected void holdElephantToRefrigerator() {
        System.out.println("把大象装冰箱");
    }

    @Override
    protected void closeRefrigeratorDoor() {
        System.out.println("关闭冰箱门");
    }
}
