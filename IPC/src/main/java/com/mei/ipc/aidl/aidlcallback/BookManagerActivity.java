package com.mei.ipc.aidl.aidlcallback;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mei.ipc.aidl.Book;
import com.mei.ipc.aidl.IBookManager;
import com.mei.ipc.aidl.IOnNewBookArrivedListener;
import com.ubt.ipc.R;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {

    private static final String TAG = "BookManagerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;//有新书来的时候
    private IBookManager mRemoteBookManager;

    /**
     * Binder是可能意外死亡的，这往往是由服务端进程意外停止了，这时我们需要重新连接服务，具体监听方法有两种：
     * 1、给Binder设置死亡代理DeathRecipient，在Binder死亡的时候，就会回调死亡代理的binderDied方法。
     * 2、在ServiceConnection的onServiceDisconnected方法中重连远程服务。
     * 区别：onServiceDisconnected方法在客户端的UI线程中被回调，而binderDied方法在客户端的Binder线程池中
     * 被回调，这就是他们的主要区别。
     * <p>
     * 监听Binder是否死亡的死亡代理
     */
    private IBinder.DeathRecipient mDeathRecipent = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied. thread name:" + Thread.currentThread().getName());
            if (mRemoteBookManager == null) return;
            mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipent, 0);
            mRemoteBookManager = null;
            //todo 这里重新绑定远程服务

        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        /**
         *  我们知道，客户端调用远程服务的方法，被调用的方法运行在服务端的Binder线程池中，同时客户端线程
         *  会被挂起，这个时候，如果服务端方法执行比较耗时，就会导致客户端线程长时间阻塞在这里，而如果这个
         *  客户端线程是UI线程的话，就会导致客户端ANR，这当然不是我们想要看到的。
         *  因此，如果我们明确知道某个远程方法是耗时的，那么就要避免在客户端的UI线程中去访问远程方法。
         *  由于客户端的onServiceConnected和onServiceDisconnected方法都运行在UI线程中，所以也不可以在
         *  它们里面直接调用服务端的耗时方法，需要另开线程，这点要尤其注意。
         *
         *  另外，由于服务端的方法本身就运行在服务端的Binder线程池中，所以服务端方法本身就可以执行大量耗时
         *  操作，这个时候切记不要在服务端方法中开线程去执行异步任务，除非你明确知道自己在干什么，否则不建议
         *  这么做。
         *
         */

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //把Binder对象转换成AIDL接口
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                mRemoteBookManager = bookManager;
                /**给Binder设置一个死亡代理，当我们到服务端Binder连接断裂的时候（称之为Binder死亡），就会回调
                 * 死亡代理的binderDied方法，这样我们就可以监听Binder的死亡时间，当Binder死亡的时候，重新发起
                 * 连接请求从而恢复连接*/
                mRemoteBookManager.asBinder().linkToDeath(mDeathRecipent, 0);

                //绑定成功后，通过aidl接口获取其他进程的图书信息
                List<Book> list = bookManager.getBookList();
                Log.i(TAG, "query book list, list type:"
                        + list.getClass().getCanonicalName());
                Log.i(TAG, "query book list:" + list.toString());

                //在客户端给服务端添加一本书
                Book newBook = new Book(3, "Android开发艺术探索");
                bookManager.addBook(newBook);
                Log.i(TAG, "add book:" + newBook);
                List<Book> newList = bookManager.getBookList();
                Log.i(TAG, "query book list:" + newList);

                //注册监听器，当服务端有新书到来的时候，通知客户端
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRemoteBookManager = null;
            Log.e(TAG, "binder died ");
            Log.d(TAG, "onServiceDisconnected.thread name:" + Thread.currentThread().getName());
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        /**
         * 当远程服务端调用客户端的Listener中的方法时，如调用onNewBookArrived方法，被调用的方法也是运行在
         * Binder线程池中的，只不过是客户端的Binder线程池中。
         *
         * 所以，我们同样不可以在服务端中调用客户端的耗时方法。
         *
         * 比如针对BookManagerService的onNewBookArrived方法，如下所示。在它的内部调用了客户端的IOnNewBookArrivedListener
         * 中的onNewBookArrived方法，如果客户端的这个onNewBookArrived方法比较耗时的话，那么请确保
         * BookManagerService中的onNewBookArrived运行在非UI线程中，否则将会导致服务端无法响应。
         *
         * 由于onNewBookArrived方法是运行在客户端的Binder线程池中，所以不能再它里面去访问UI相关的内容，
         * 如果要访问UI，切换的主线程之后再进行。
         */

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            //服务端有新书到来时，回调该方法onNewBookArrived，该方法在binder线程池中执行的，所有需要切换到UI线程
            //中刷新UI
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d(TAG, "receive new book:" + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void onButton1Click(View view) {
        Toast.makeText(this, "click Button1", Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //我们在服务端模拟了一下，假设getBookList方法是一个耗时的方法，这个时候反复调用就会出现ANR，
                    //这时就需要把该操作放入到工作线程中去执行.
                    if (mRemoteBookManager != null) {
                        mRemoteBookManager.getBookList();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.i(TAG, "unregister listener :" + mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
