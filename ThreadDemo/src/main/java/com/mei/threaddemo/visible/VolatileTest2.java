package com.mei.threaddemo.visible;

/**
 * @author mxb
 * @date 2022/5/24
 * @desc
 * @desired
 */
public class VolatileTest2 {

    public static class RunThread extends Thread {

        private volatile boolean isRunning = true;

        @Override
        public void run() {
            super.run();
            System.out.println("线程开始执行");
            while (isRunning) {

            }
            System.out.println("线程执行结束");
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(1000);
        runThread.setRunning(false);
        System.out.println("已经设置子线程 isRunning 为 false 了");
    }
}
