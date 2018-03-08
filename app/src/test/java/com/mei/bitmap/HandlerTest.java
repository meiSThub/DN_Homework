package com.mei.bitmap;

import com.mei.test.optimize.handler.Handler;
import com.mei.test.optimize.handler.Looper;
import com.mei.test.optimize.handler.Message;

import org.junit.Test;

/**
 * Created by mei on 2018/3/8.
 * Description:
 */

public class HandlerTest {

    @Test
    public void test() {

        Looper.prepare();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //主线程接收消息
                System.out.println(Thread.currentThread() + " recv:" + msg.obj);
            }
        };

        new Thread("子线程1") {
            @Override
            public void run() {
                super.run();
                //在子线程发送消息
                System.out.println(Thread.currentThread() + "send");
                Message message = new Message();
                message.obj = "hello";
                handler.sendMessage(message);
            }
        }.start();

        final Looper looper = Looper.myLooper();
        new Thread("子线程2") {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //1ms之后，退出主线程的Loop循环
                looper.quit();

                System.out.println(Thread.currentThread() + "send");
                Message message = new Message();
                message.obj = "hello2";
                handler.sendMessage(message);
            }
        }.start();

        Looper.loop();
    }
}
