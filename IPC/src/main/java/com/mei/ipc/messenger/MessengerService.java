package com.mei.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ubt.ipc.utils.MyConstants;

/**
 * Created by ubt on 2018/2/24.
 *
 * @description: 服务端进程
 */

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";


    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_CLIENT:
                    //模拟接收到客户端进程的发送来的消息
                    Log.i(TAG, "receive msg from Client:" +
                            msg.getData().getString("msg"));

                    //在接收到客户端进程发送来的消息后，立马回复客户端进程
                    Messenger client = msg.replyTo;//取出客户端进程设置的接收服务端进程消息的Messenger
                    Message replyMessage = Message.obtain(null, MyConstants.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "嗯，你的消息我已经收到了，稍后会回复你。");
                    replyMessage.setData(bundle);
                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Messenger的内部是通过AIDL来实现进程间通信的，当Messenger内的Binder接收到客户端发送来的消息后，就会
     * 通过给定的Handler来派发消息
     */
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

}
