package com.mei.design;

import com.mei.design.mode16State.demo1.Lift;
import com.mei.design.mode16State.demo2.Work;

import org.junit.Test;



/**
 * Created by ubt on 2018/1/17.
 *
 * @description:状态模式测试用例
 */

public class StateTest {
    @Test
    public void test() {
        Lift lift = new Lift();
        lift.setCurrState(lift.getClosingState());
        lift.open();
        lift.close();
        lift.run();
        lift.stop();
    }

    @Test
    public void test2() {
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
