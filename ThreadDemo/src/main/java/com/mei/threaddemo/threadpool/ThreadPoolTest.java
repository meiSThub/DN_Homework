package com.mei.threaddemo.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author mxb
 * @date 2022/5/26
 * @desc
 * @desired
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20,
                0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 100; i++) {
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务->" + (index + 1));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
