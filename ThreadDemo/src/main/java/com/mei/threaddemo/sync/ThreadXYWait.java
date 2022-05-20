package com.mei.threaddemo.sync;

/**
 * @author mxb
 * @date 2022/5/20
 * @desc 两个线程交替打印奇数和偶数
 * @desired
 */
public class ThreadXYWait {

    // 当前数字
    private int num = 0;

    private final Object lock = new Object();

    private void printXY(int threadIndex) {
        synchronized (lock) {
            while (num < 100) {
                System.out.println(Thread.currentThread().getName() + ":" + num++);
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        final ThreadXYWait threadXYWait = new ThreadXYWait();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadXYWait.printXY(0);
            }
        }, "偶数").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadXYWait.printXY(1);
            }
        }, "奇数").start();
    }
}
