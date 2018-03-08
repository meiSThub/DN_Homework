package com.mei.test.optimize.handler;

/**
 * Created by mei on 2018/3/8.
 * Description:
 */

public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    //消息队列
    public MessageQueue mQueue;


    private Looper() {
        mQueue = new MessageQueue();
    }

    /**
     * 为调用此函数的线程准备Looper
     * 准备looper
     */
    public static void prepare() {
        //当前线程已经存在looper就抛异常
        if (null != sThreadLocal.get()) {
            throw new RuntimeException(Thread.currentThread() + "已经存在一个Looper对象");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 获得当前线程的looper对象
     *
     * @return
     */
    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    /**
     * 不停的从messageQueue 中获取message
     */
    public static void loop() {
        //获取当前线程的Looper对象
        Looper looper = Looper.myLooper();
        MessageQueue queue = looper.mQueue;
        for (; ; ) {
            //获取到Message
            Message next = queue.next();
            //获取消息为空的话，退出死循环,说明已经调用了quit方法了，
            //如果没有quit的话，那么next（）方法会被阻塞，而不会返回null
            if (next == null) {
                break;
            }
            //分发到发送message的handler执行
            next.target.handleMessage(next);
        }
    }

    /**
     * 推出
     */
    public void quit() {
        mQueue.quit();
    }
}
