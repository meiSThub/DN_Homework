package com.mei.threaddemo;

/**
 * @author mxb
 * @date 2022/5/18
 * @desc
 * @desired
 */
public class Thread3Wait {

    static final Object lock = new Object();

    public static void main(String[] args) {
        final Thread3Wait thread3Wait = new Thread3Wait();
        // 打印数字
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread3Wait.print();
            }
        }, "number").start();
        // 打印字母
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread3Wait.print();
            }
        }, "letter").start();

    }

    private void print() {
        try {
            synchronized (lock) {
                String threadName = Thread.currentThread().getName();
                for (int i = 0; i < 26; i++) {
                    if (threadName.equals("number")) {
                        System.out.print("" + (i + 1));
                    } else {
                        System.out.println("" + (char) ('A' + i));
                    }
                    // 如果number线程获取到lock对象锁，则这里就是唤醒letter线程，接着让number线程自己进入等待
                    // 如果letter线程获取到lock对象锁，则这里就是唤醒number线程，接着让letter线程自己进入等待
                    lock.notifyAll();// 唤醒所有在等待队列中的线程，被唤醒的线程变为可执行状态
                    lock.wait();// 当前线程释放lock对象锁，并进入等待队列，等待被唤醒
                }
                // 假设number线程和letter线程中，i都等于25，即执行到最后一次for循环了。
                // 1、number线程执行：先完成打印，接着唤醒letter线程，并让自己进入等待。
                // 2、letter线程获取到lock对象锁，letter线程执行，先完成打印，接着唤醒number线程，并让自己进入等待。
                // 3、number线程获取到lock对象锁，number线程执行：此时i=26,不满足for循环条件，则退出for循环，
                // 如果这里不在调用一次lock.notifyAll()方法，那么letter线程将会一直等待下去，而number线程则可以正常结束
                // 所以，这里需要额外调用一次lock对象的notifyAll()方法。
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
