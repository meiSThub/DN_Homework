package com.mei.arouter;

import com.alibaba.android.arouter.launcher.ARouter;

import android.app.Application;

/**
 * @author mxb
 * @date 2022/5/31
 * @desc
 * @desired
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 必须在初始化ARouter之前配置
        if (BuildConfig.DEBUG){
            // 日志开启
            ARouter.openLog();
            // 调试模式开启，如果在install run模式下运行，则必须开启调试模式
            ARouter.openDebug();
        }
        // ARouter 初始化
        ARouter.init(this);
    }
}
