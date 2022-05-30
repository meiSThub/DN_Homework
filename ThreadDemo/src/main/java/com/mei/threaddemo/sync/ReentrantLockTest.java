package com.mei.threaddemo.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mxb
 * @date 2022/5/26
 * @desc
 * @desired
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        test.lockTest1();
    }

    public void lockTest1() {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();// 获取锁
            System.out.println("hold count:" + lock.getHoldCount());
            try {
                if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    System.out.println("hold count:" + lock.getHoldCount());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();// 释放锁
            }
            System.out.println("hold count:" + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
        System.out.println("after unlock ,hold count:" + lock.getHoldCount());
    }

}
