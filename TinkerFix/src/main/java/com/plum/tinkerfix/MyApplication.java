package com.plum.tinkerfix;

import android.app.Application;
import android.content.Context;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        FixManager.loadDex(base);//app每次启动的时候，都重新加载一次dex文件
        super.attachBaseContext(base);
    }
}
