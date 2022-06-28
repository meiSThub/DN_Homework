package com.plum.processkeepalive.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

public class ForgroundService extends Service {

    private static final int SERVICE_ID = 10;

    @Override
    public void onCreate() {
        super.onCreate();
        //让服务变成前台服务
        startForeground(SERVICE_ID, new Notification());
        //如果 api>18 的设备，启动一个Service ，startForeground给
        //相同的id，然后结束这个Service,这样的前台服务就不会在状态栏上显示通知了
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startService(new Intent(this, InnerService.class));
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
