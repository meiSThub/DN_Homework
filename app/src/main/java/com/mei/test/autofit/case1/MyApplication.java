package com.mei.test.autofit.case1;

import android.app.Application;

/**
 * Created by mei on 2017/11/25.
 * Description:需要重写Application
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
