package com.mei.threaddemo;

/**
 * @author mxb
 * @date 2022/5/18
 * @desc yield 方法使用
 * @desired
 */
public class Thread4Join {

    public static void main(String[] args) throws Exception {
        // joinTest();
        // lockObject();
        lockThread();
    }

    private static void joinTest() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread=" + Thread.currentThread().getName() + " 开始执行");
                System.out.println("thread=" + Thread.currentThread().getName() + " 结束");
            }
        }, "A");
        thread.start();// 线程A 进入可执行状态
        // 调用 线程A 的join方法，当前线程（主线程）会进入等待中的状态，且已经持有的锁不会释放
        // thread.join() 和 thread.join(0) 两个方法的功能是一样的，都是死等，即当前线程会等线程A执行完成，才会进入可执行状态
        // thread.join(millis)，相当于设置了一个超时时间，等到millis时间过了之后，不管线程A是否执行完成，主线程都会进入阻塞状态
        System.out.println("主线程，开始join");
        thread.join();
        // thread.join(500);
        System.out.println("主线程，结束join");
        System.out.println("主线程，执行结束");
    }

    /**
     * 验证join方法是否会释放锁：该方法会造成死锁
     * 1. 主线程持有 lock对象锁，在同步代码块中，调用了 线程A 的 join 方法，即主线程需要等待 线程A 执行完成之后在执行。
     * 2. 主线程调用 线程A 的 join方法，如果主线程原先持有 线程A的对象锁，则会释放该对象锁。
     * 3. 线程A执行，需要 lock对象锁，而lock对象锁 被主线程所持有，所以线程A会一直等待下去。
     * 4. 主线程又在等待 线程A 执行完毕才会被唤醒，所以 主线程和线程A就造成死锁了。
     */
    private static void lockObject() throws Exception {
        final Object lock = new Object();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread=" + Thread.currentThread().getName() + " 开始执行");
                synchronized (lock) {// 线程A 又要获取 lock对象锁，而lock对象锁 被主线程所持有且没有释放，
                    // 所以子线程会一直等待主线程释放 lock对象锁，而主线程又在等 线程A执行完毕，从而唤醒主线程，所以就造成死锁了。
                    System.out.println("同步代码块，thread=" + Thread.currentThread().getName());
                }
                System.out.println("thread=" + Thread.currentThread().getName() + " 结束");
            }
        }, "A");
        threadA.start();// 线程A 进入可执行状态
        synchronized (lock) {// 获取lock对象锁，
            System.out.println("主线程，进入同步代码块");
            System.out.println("开始join");
            threadA.join();// join 是一个同步方法，进入该方法需要获取 threadA对象锁，而join内部又调用了 Object 的wait方法，会释放 threadA对象锁，但lock对象锁不会被释放
            System.out.println("结束join");
            threadA.wait();
            // thread.join(500);
            System.out.println("主线程，执行结束");
        }
    }

    /**
     * 验证join方法是否会释放锁：该方法 不会 造成死锁
     * 1. 主线程持有 线程A的对象锁，在同步代码块中，调用了 线程A 的 join 方法，即主线程需要等待 线程A 执行完成之后在执行。
     * 2. 主线程调用 线程A 的 join方法，内部调用的是Object的 wait 方法，主线程会释放 线程A的对象锁。
     * 3. 线程A执行，需要 线程A的对象锁，而主线程在调用 线程A的join方法时，已经释放了 线程A的对象锁，所以线程A可以获取到 线程A的对象锁，即线程A正常执行。
     * 4. 线程A执行完毕，JVM会调用 线程A的 notify() 或者 notifyAll() 方法，唤醒所有等待线程A对象锁的线程，所以主线程被唤醒。
     * 5. 在线程A执行完成之后，唤醒主线程，主线程正常执行，所以程序运行正常。
     */
    private static void lockThread() throws Exception {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread=" + Thread.currentThread().getName() + " 开始执行");
                synchronized (this) {
                    System.out.println("同步代码块，thread=" + Thread.currentThread().getName());
                }
                System.out.println("thread=" + Thread.currentThread().getName() + " 结束");
            }
        }, "A");
        threadA.start();// 线程A 进入可执行状态
        synchronized (threadA) {
            System.out.println("主线程，进入同步代码块");
            // 调用 线程A 的join方法，当前线程（主线程）会进入等待中的状态，且已经持有的锁不会释放
            // thread.join() 和 thread.join(0) 两个方法的功能是一样的，都是死等，即当前线程会等线程A执行完成，才会进入可执行状态
            // thread.join(millis)，相当于设置了一个超时时间，等到millis时间过了之后，不管线程A是否执行完成，主线程都会进入阻塞状态
            System.out.println("主线程，开始join");
            threadA.join();
            // thread.join(500);
            System.out.println("主线程，结束join");
            System.out.println("主线程，执行结束");
        }
    }
}
