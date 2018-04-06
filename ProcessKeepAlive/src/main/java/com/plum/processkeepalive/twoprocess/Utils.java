package com.plum.processkeepalive.twoprocess;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

public class Utils {

    public static boolean inRunningService(Context context, String name) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfoList = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : runningServiceInfoList) {
            if (TextUtils.equals(info.service.getClassName(), name)) {
                return true;
            }
        }
        return false;
    }
}
