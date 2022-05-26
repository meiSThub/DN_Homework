package com.mei.threaddemo.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author mxb
 * @date 2022/5/26
 * @desc
 * @desired
 */
public class ThreadPoolTest2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // 最终结论，并不会创建100个线程，当有线程空闲的时候，会进行线程的复用，每个任务都很快，
        // 如果任务比较耗时，则会创建100个线程
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" 执行任务->" + (index + 1));
                    // try {
                    //     Thread.sleep(1000);
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }
                }
            });
        }
    }
}
