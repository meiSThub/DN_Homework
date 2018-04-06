package com.plum.processkeepalive.activitykeepalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by mei on 2018/4/6.
 * Description:监听屏幕的息屏和亮屏广播
 */

public class KeepReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "receive:" + action);
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
            //息屏时 开启1px activity
            KeepManager.getInstance().startKeep(context);
        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
            KeepManager.getInstance().finishKeep();
        }
    }
}
