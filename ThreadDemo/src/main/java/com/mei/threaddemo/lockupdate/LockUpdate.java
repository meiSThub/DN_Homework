package com.mei.threaddemo.lockupdate;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author mxb
 * @date 2022/5/30
 * @desc 锁升级demo
 * @desired
 */
public class LockUpdate {

    public static void main(String[] args) throws InterruptedException {
        LockUpdate lockTemp = new LockUpdate();
        // 在默认情况下，新创建的对象，都处于无锁状态
        System.out.println("无状态(001):" + parseObj(lockTemp));
        Thread.sleep(5000);
        // jvm 默认延时4s自动开启偏向锁，可以通过 -XX:BiasedLockingStartupDelay=0 取消延时；
        // 如果不要偏向锁，可以通过 -XX:-UseBiasedLocking = false 来设置
        final LockUpdate lock = new LockUpdate();// 因为休眠来5s，所以此时创建的所有对象，默认都是偏向锁。
        System.out.println("启用偏向锁(101):" + parseObj(parseObj(lock)));
        for (int i = 0; i < 2; i++) {
            synchronized (lock) {
                System.out.println("偏向锁(101)(带线程ID)：" + parseObj(lock));
            }
            // 这个打印是为了证明偏向锁是不会自动释放的
            System.out.println("偏向锁释放(101)(带线程ID)：" + parseObj(lock));
        }
        // 只要有线程来竞争偏向锁，则偏向锁会立即升级位轻量级锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("轻量级锁(00)(带线程ID)：" + parseObj(lock));
                    try {
                        System.out.println("睡眠3s钟--------------");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("轻量-->重量(10)：" + parseObj(lock));
                }
            }
        }).start();
        Thread.sleep(1000);
        // 模拟多个线程争抢锁，这时就升级为重量级锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("重量级锁(10)：" + parseObj(lock));
                }
            }
        }).start();
    }

    private static String parseObj(Object obj) {
        // 实现对象存储结构的打印
        return ClassLayout.parseInstance(obj).toPrintable();
    }
}
