package com.mei.test.optimize.handler;

/**
 * Created by mei on 2018/3/8.
 * Description:
 */

public class Handler {


    //消息队列
    private MessageQueue mQueue;

    public Handler() {
        //获得当前线程的Looper以及MessageQueue
        Looper mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(Message message){
        message.target=this;
        mQueue.enqueueMessage(message);
    }

    public void handleMessage(Message msg) {

    }
}
