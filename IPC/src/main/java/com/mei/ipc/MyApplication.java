package com.mei.ipc;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.ubt.ipc.utils.MyUtils;


/**
 * Created by ubt on 2018/2/24.
 *
 * @description:
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(), Process.myPid());
        Log.d(TAG, "application start, process name:" + processName);
    }
}
