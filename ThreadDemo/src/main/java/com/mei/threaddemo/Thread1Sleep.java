package com.mei.threaddemo;

/**
 * @author mxb
 * @date 2022/5/18
 * @desc sleep 方法使用
 * @desired
 */
public class Thread1Sleep {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("currentThread=" + Thread.currentThread().getName());
            }
        });
        thread.start();// 启动线程
        // 主线程休眠500毫秒
        Thread.sleep(500);
        // 在主线程中调用 sleep 方法，主线程会休眠500毫秒，主线程的状态就变为超时等待状态（TIMED_WAITING）
        // 500毫秒之后，主线自动变成可执行状态（RUNNABLE）
        System.out.println("主线程");
    }
}
