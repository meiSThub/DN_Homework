package com.mei.design.mode16State.demo1;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:
 */

public class Test {

    public static void main(String[] args) {
        Lift lift = new Lift();
        lift.setCurrState(lift.getClosingState());
        lift.open();
        lift.close();
        lift.run();
        lift.stop();
    }
}
