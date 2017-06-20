package com.mei.test.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mei.test.DemoApplication;
import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/5/19.
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static String TAG = BaseActivity.class.getSimpleName();
    protected DemoApplication mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (DemoApplication) getApplication();

        Class cl = getClass();
        LogUtils.e(TAG, "class=" + cl);
        mApplication.put(cl, getItemTitle());
    }

    public String getItemTitle() {
        return null;
    }
}
