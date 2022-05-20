package com.mei.threaddemo.sync;

import java.util.concurrent.locks.LockSupport;

/**
 * @author mxb
 * @date 2022/5/20
 * @desc
 * @desired
 */
public class ThreadABCLockSupport {

    private static Thread mThreadA, mThreadB, mThreadC;

    public static void main(String[] args) {
        mThreadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // System.out.println("线程" + Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    System.out.print(Thread.currentThread().getName());
                    LockSupport.unpark(mThreadB);// 唤醒线程B
                    LockSupport.park();// 阻塞当前线程
                }
            }
        }, "A");
        mThreadB = new Thread(new Runnable() {
            @Override
            public void run() {
                // System.out.println("线程" + Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    LockSupport.park();
                    System.out.print(Thread.currentThread().getName());
                    LockSupport.unpark(mThreadC);
                }
            }
        }, "B");
        mThreadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // System.out.println("线程" + Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    LockSupport.park();// 先阻塞当前线程，等待被唤醒
                    System.out.println(Thread.currentThread().getName());
                    LockSupport.unpark(mThreadA);// 唤醒线程A
                }
            }
        }, "C");
        mThreadA.start();
        mThreadB.start();
        mThreadC.start();
    }
}
