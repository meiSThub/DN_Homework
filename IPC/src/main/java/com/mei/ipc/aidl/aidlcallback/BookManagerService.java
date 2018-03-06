package com.mei.ipc.aidl.aidlcallback;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.ubt.ipc.aidl.Book;
import com.ubt.ipc.aidl.IBookManager;
import com.ubt.ipc.aidl.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ubt on 2018/2/24.
 *
 * @description:
 */

public class BookManagerService extends Service {

    private static final String TAG = "BMS";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    //保存回调接口
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    // private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList =
    // new CopyOnWriteArrayList<IOnNewBookArrivedListener>();

    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            //权限验证方法二：在onTransact方法中，添加权限验证代码
            int check = checkCallingOrSelfPermission("com.ubt.ipc.permission.ACCESS_BOOK_SERVICE");
            Log.d(TAG, "check=" + check);
            if (check == PackageManager.PERMISSION_DENIED) {
                Log.e(TAG, "onTransact. 权限验证失败");
                return false;
            }
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (TextUtils.isEmpty(packageName) || !packageName.startsWith("com.ubt")) {
                Log.e(TAG, "onTransact. 权限验证失败,包名必须以com.ubt开头");
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            //模拟假设服务端的getBookList()方法是一个耗时的方法
            SystemClock.sleep(10000);
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.register(listener);

            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "registerListener, current size:" + N);
            /*if (!mListenerList.contains(listener)) {
                mListenerList.add(listener);
            } else {
                Log.d(TAG, "already exists.");
            }
            Log.d(TAG, "registerListener size:" + mListenerList.size());
            */
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            boolean success = mListenerList.unregister(listener);
            if (success) {
                Log.d(TAG, "unregister success.");
            } else {
                Log.d(TAG, "not found, can not unregister.");
            }
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener, current size:" + N);

            /*if (mListenerList.contains(listener)) {
                mListenerList.remove(listener);
                Log.d(TAG, "unregister listener successd.");
            } else {
                Log.d(TAG, "not found,can not unregister.");
            }
            Log.d(TAG, "unregisterListener,current size:" + mListenerList.size());
            */
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));
        //开启一个线程，每个5秒添加一本书
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /**
         * 权限验证方法一：在onBind方法中，添加权限验证代码
         *
         * 添加权限验证功能，权限验证失败的人无法连接到该服务
         */
        int check = checkCallingOrSelfPermission("com.ubt.ipc.permission.ACCESS_BOOK_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED) {
            Log.e(TAG, "onBind，连接权限验证未通过");
            return null;
        }
        Log.d(TAG, "onBind，连接权限验证成功");
        return mBinder;
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        /*Log.d(TAG, "onNewBookArrived,notify listeners:" + mListenerList.size());
        for (int i = 0; i < mListenerList.size(); i++) {
            IOnNewBookArrivedListener listener = mListenerList.get(i);
            Log.d(TAG, "onNewBookArrived,notify listener:" + listener);
            listener.onNewBookArrived(book);
        }*/

        final int N = mListenerList.beginBroadcast();
        Log.d(TAG, "onNewBookArrived,notify listeners:" + N);
        try {
            for (int i = 0; i < N; i++) {
                IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
                if (listener != null) {
                    Log.d(TAG, "onNewBookArrived,notify listener:" + listener);
                    listener.onNewBookArrived(book);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //必须与beginBroadcast成对出现，否则会出异常
        mListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
