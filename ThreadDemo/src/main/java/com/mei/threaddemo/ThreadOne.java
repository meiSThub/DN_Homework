package com.mei.threaddemo;

/**
 * @author mxb
 * @date 2022/5/17
 * @desc
 * @desired
 */
public class ThreadOne implements Runnable {

    Accum mAccum = Accum.getAccum();

    @Override
    public void run() {
        for (int i = 0; i < 98; i++) {
            mAccum.updateCounter(1000);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("one " + mAccum.getCounter());
    }
}
