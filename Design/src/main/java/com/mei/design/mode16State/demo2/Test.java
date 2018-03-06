package com.mei.design.mode16State.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: 一个操作中含有庞大的分支结构，并且这些分支决定于对象的状态。测试用例
 */

public class Test {

    public static void main(String[] args) {
        Work work = new Work();
        work.setHour(9);
        work.writeProgram();
        work.setHour(10);
        work.writeProgram();
        work.setHour(12);
        work.writeProgram();
        work.setHour(13);
        work.writeProgram();
        work.setHour(14);
        work.writeProgram();
        work.setHour(17);
        work.writeProgram();
        work.setFinishWork(true);
        work.writeProgram();
        work.setHour(19);
        work.writeProgram();
        work.setHour(22);
        work.writeProgram();

    }
}
