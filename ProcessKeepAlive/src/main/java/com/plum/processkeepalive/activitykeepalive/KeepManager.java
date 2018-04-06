package com.plum.processkeepalive.activitykeepalive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * Created by mei on 2018/4/6.
 * Description:Activity提权保活，广播与透明activity的管理类
 */

public class KeepManager {
    private static final KeepManager ourInstance = new KeepManager();

    private WeakReference<Activity> mKeepActivity;
    private KeepReceiver keepReceiver;

    public static KeepManager getInstance() {
        return ourInstance;
    }

    private KeepManager() {
    }

    /**
     * 注册 开屏 关屏关公
     *
     * @param context
     */
    public void registerKeep(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        keepReceiver = new KeepReceiver();
        context.registerReceiver(keepReceiver, intentFilter);
    }

    /**
     * 反注册广播接收者
     *
     * @param context
     */
    public void unregisterKeep(Context context) {
        if (null != keepReceiver) {
            context.unregisterReceiver(keepReceiver);
        }
    }

    /**
     * 开启Activity
     *
     * @param context
     */
    public void startKeep(Context context) {
        Intent intent = new Intent(context, KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void finishKeep() {
        if (null != mKeepActivity) {
            Activity activity = mKeepActivity.get();
            if (null != activity) {
                activity.finish();
            }
            mKeepActivity = null;
        }
    }

    public void setKeep(KeepActivity keep) {
        mKeepActivity = new WeakReference<Activity>(keep);
    }
}
