package com.mei.threaddemo.sync;

/**
 * @author mxb
 * @date 2022/5/20
 * @desc 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * @desired
 */
public class ThreadABCWait {

    // 打印总次数
    private final int times;

    // 执行线程下标
    private int index = 0;

    public ThreadABCWait(int times) {
        this.times = times;
    }

    private void printABC(int threadIndex) {
        for (int i = 0; i < times; ) {
            synchronized (this) {
                // 1.不满足条件，则线程等待
                while (index % 3 != threadIndex) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 2.满足条件就打印日志，并递增
                if (threadIndex == 2) {
                    System.out.println(Thread.currentThread().getName());
                } else {
                    System.out.print(Thread.currentThread().getName());
                }
                i++;
                index++;
                // 唤醒等待的线程
                this.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        final ThreadABCWait threadABC = new ThreadABCWait(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadABC.printABC(0);
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadABC.printABC(1);
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadABC.printABC(2);
            }
        }, "C").start();
    }
}
