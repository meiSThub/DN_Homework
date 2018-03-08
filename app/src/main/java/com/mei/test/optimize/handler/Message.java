package com.mei.test.optimize.handler;

/**
 * Created by mei on 2018/3/8.
 * Description:
 */

public class Message {

    public int what;
    public Object obj;
    public Message next;

    //使此handler发送的消息，则需要分发到
    Handler target;

    public void recycle() {
        obj = null;
        next = null;
        target = null;
    }

}
