package com.mei.threaddemo;

/**
 * @author mxb
 * @date 2022/5/18
 * @desc yield 方法使用
 * @desired
 */
public class Thread2Yield {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread=" + Thread.currentThread().getName());
            }
        },"A");
        thread.start();// 线程A 进入可执行状态

        // 主线程放弃CPU时间片，但不会放弃锁，此时主线程进入可运行状态
        // 此时主线程跟其它的可运行状态的线程有相同的机会被CPU选中，从而继续运行
        // 跟sleep(mills)方法类型，区别在于sleep方法可以执行休眠但时间，而yield方法不可以
        Thread.yield();
        System.out.println("主线程");
    }
}
