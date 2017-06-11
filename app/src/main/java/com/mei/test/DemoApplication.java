package com.mei.test;

import android.app.Application;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mei on 2017/6/9.
 * Description:
 */
public class DemoApplication extends Application {
    public static Map<Class, String> mActions = new HashMap<>();

    public void put(Class key, String title) {
        if (key != null && !TextUtils.isEmpty(title)) {
            mActions.put(key, title);
        }
    }

    public String getTitle(String key) {
        return mActions.get(key);
    }

    public Map<Class, String> getActions() {
        return mActions;
    }
}
