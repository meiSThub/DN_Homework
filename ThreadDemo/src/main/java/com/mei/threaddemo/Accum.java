package com.mei.threaddemo;

/**
 * @author mxb
 * @date 2022/5/17
 * @desc
 * @desired
 */
public class Accum {

    private static Accum sAccum = new Accum();

    private int counter = 0;

    public static Accum getAccum() {
        return sAccum;
    }

    public void updateCounter(int add) {
        counter += add;
    }

    public int getCounter() {
        return counter;
    }
}
