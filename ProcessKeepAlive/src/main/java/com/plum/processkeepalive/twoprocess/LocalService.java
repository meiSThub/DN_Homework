package com.plum.processkeepalive.twoprocess;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * Created by mei on 2018/4/6.
 * Description:本地进程
 */

public class LocalService extends Service {

    private static final String TAG = "Service";

    private static final int SERVICE_ID = 10;

    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }

    private MyBinder mMyBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyBinder = new MyBinder();
        mServiceConnection = new ServiceConnection();

        //结合service提权
        startForeground(SERVICE_ID, new Notification());
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startService(new Intent(this, InnerService.class));
        }
    }

    private ServiceConnection mServiceConnection;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, RemoteService.class), mServiceConnection, BIND_AUTO_CREATE);
        return super.onStartCommand(intent, flags, startId);
    }

    class ServiceConnection implements android.content.ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //服务连接后回调
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG,"子进程可能被干掉了，拉活");

            //连接中断后回调,
            //与LocalService的连接中断后，重新启动LocalService，并与之绑定监听,防止LocalService被杀
            /*先startService在bindService的目的在于，如果在其他地方调用了unBindService方法，Service就不会
            * 马上停掉，因为我们还调用了startService方法*/
            startService(new Intent(LocalService.this, RemoteService.class));
            bindService(new Intent(LocalService.this, RemoteService.class),
                    mServiceConnection, BIND_AUTO_CREATE);

        }
    }

    public static class InnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            startForeground(SERVICE_ID, new Notification());
            stopSelf();
        }
    }
}
