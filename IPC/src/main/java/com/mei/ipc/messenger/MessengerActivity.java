package com.mei.ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ubt.ipc.R;
import com.ubt.ipc.utils.MyConstants;

public class MessengerActivity extends AppCompatActivity {
    private static final String TAG = "MessengerActivity";

    private Messenger mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /**
             *构建一个用于向服务端进程发送消息的Messenger
             *在bindService的时候，会调用service的onBind方法，然后会回调onServiceConnected方法，第二个参数
             * service就是onBind方法返回的值，通过参数service就可以构建一个向指定Service发送消息的Messenger，
             * 即service就是发送消息的地址。
             */
            mService = new Messenger(service);
            Message msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello,this is client");
            msg.setData(data);
            //通过replyTo字段，设置接收并处理服务端进程回复消息的Messenger，则当服务端进程需要回复客户端进程的时候，
            //就会从客户端进程传入的Message中，取出replyTo，回复客户端进程
            msg.replyTo = mGetReplyMessenger;
            try {
                mService.send(msg);//向服务端进程发送消息
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_SERVICE:
                    Log.i(TAG, "receive msg from Service:" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(mConnection);
    }
}
