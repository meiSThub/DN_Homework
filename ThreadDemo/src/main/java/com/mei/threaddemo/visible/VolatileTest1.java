package com.mei.threaddemo.visible;

/**
 * @author mxb
 * @date 2022/5/24
 * @desc
 * @desired
 */
public class VolatileTest1 {

    public static class RunThread extends Thread {

        private boolean isRunning = true;

        @Override
        public void run() {
            super.run();
            System.out.println("线程开始执行");
            while (isRunning) {

            }
            // 在启动RunThread线程时，变量private boolean isRunning = true;存在于公共堆栈以及线程的私有
            // 堆栈中。JVM为了线程运⾏的效率，线程⼀直在私有堆栈中取得isRunning的值是true。⽽代码
            // thread.setRunning(false);虽然被执⾏，更新的却是公共堆栈中的isRunning变量的值false，所以线程⼀
            // 直死循环状态。
            System.out.println("线程执行结束");
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建RunThread对象，对此存储在公共堆栈中，当 RunThread 当作一个线程执行的时候，会把共享变量复制一份到线程
        // 的私有工作内存中，当调用 runThread 对象的 setRunning（boolean）方法修改 isRunning 变量的时候，修改的
        // 是公共堆栈中的 isRunning 值，而这个共享变量由于缺少同步机制，导致更新的值无法及时同步到 RunThread线程中，
        // 所以导致RunThread线程中的 isRunning 变量一直为true，导致该线程死循环而无法退出。
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(1000);
        runThread.setRunning(false);
        System.out.println("已经设置子线程 isRunning 为 false 了");
    }
}
