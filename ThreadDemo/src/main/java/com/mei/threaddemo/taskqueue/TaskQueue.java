package com.mei.threaddemo.taskqueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mxb
 * @date 2022/5/20
 * @desc 任务队列
 * @desired
 */
public class TaskQueue {

    private Deque<String> queue = new LinkedList<>();

    public synchronized void addTask(String task) {
        queue.add(task);
        // 2.现在我们面临第二个问题：如何让等待的线程被重新唤醒，然后从wait()方法返回？答案是在相同的锁对象上调用notify()方法。
        // 唤醒所有在等待队列中的线程
        this.notifyAll();
    }

    public synchronized String getTask() {
        try {
            // 1.在条件不满足时，线程进入等待状态
            while (queue.isEmpty()) {
                this.wait();// 释放 TaskQueue对象锁，并进入等待队列
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return queue.poll();
    }
}
