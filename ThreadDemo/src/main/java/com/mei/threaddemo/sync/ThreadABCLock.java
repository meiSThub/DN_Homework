package com.mei.threaddemo.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mxb
 * @date 2022/5/20
 * @desc 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * 使用Lock类实现
 * @desired
 */
public class ThreadABCLock {

    // 执行的总次数
    private final int times;

    // 当前序号
    private int currIndex;

    // 锁对象
    private Lock mLock = new ReentrantLock();

    public ThreadABCLock(int times) {
        this.times = times;
    }

    private void printABC(String msg, int threadIndex) {
        int i = 0;
        do {
            mLock.lock();// 尝试获取对象锁
            if (currIndex % 3 == threadIndex) {
                if (threadIndex == 2) {
                    System.out.println(msg);
                } else {
                    System.out.print(msg);
                }
                i++;
                currIndex++;
            }
            mLock.unlock();// 释放锁
        } while (i < times);
    }

    public static void main(String[] args) {
        final ThreadABCLock threadLock = new ThreadABCLock(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLock.printABC("A", 0);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLock.printABC("B", 1);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLock.printABC("C", 2);
            }
        }).start();
    }
}
