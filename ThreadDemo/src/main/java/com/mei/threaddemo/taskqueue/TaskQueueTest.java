package com.mei.threaddemo.taskqueue;

/**
 * @author mxb
 * @date 2022/5/20
 * @desc
 * @desired
 */
public class TaskQueueTest {

    public static void main(String[] args) {
        final TaskQueue taskQueue = new TaskQueue();
        // 创建10个线程，用于生产任务
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    // 每个线程生产10个任务
                    for (int j = 0; j < 10; j++) {
                        // 把生产的任务，放入任务队列中
                        taskQueue.addTask(threadName + "生产:task-" + (j + 1));
                        // 模拟生产耗时
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "生产这线程-" + (i + 1)).start();
        }
        // 创建10线程，用于消耗任务
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    for (int j = 0; j < 10; j++) {
                        String task = taskQueue.getTask();
                        System.out.println(threadName + " 执行任务【" + task + "】");
                    }
                }
            }, "消费者线程" + (i + 1)).start();
        }
    }
}
