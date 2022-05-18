package com.mei.threaddemo;

public class MyClass {

    public static void main(String[] args) {
        // Thread thread = new Thread() {
        //     @Override
        //     public void run() {
        //         super.run();
        //         System.out.println("子线程执行：threadName=" + Thread.currentThread());
        //     }
        // };
        // thread.start();
        // System.out.println("主线程执行");

        Runnable runnable = new Runnable() {

            int balance;

            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    increment();
                }
            }

            private synchronized void increment() {
                int i = balance;
                balance = i + 1;
                System.out.println(Thread.currentThread().getName() + " balance is " + balance);
            }
        };
        Thread thread = new Thread(runnable, "thread1");
        Thread thread2 = new Thread(runnable, "thread2");
        thread.start();
        thread2.start();

        System.out.println("主线程执行");

        // Thread one = new Thread(new ThreadOne());
        // Thread two = new Thread(new ThreadTwo());
        // one.start();
        // two.start();
    }

    private void test() {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        one.start();
        two.start();
    }
}

