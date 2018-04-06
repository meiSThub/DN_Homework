package com.plum.processkeepalive.activitykeepalive;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import static android.content.ContentValues.TAG;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

public class KeepActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "启动Keep");
        Window window = getWindow();
        //设置这个act 左上角
        window.setGravity(Gravity.START | Gravity.TOP);
        //宽 高都为1
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = 1;
        attributes.height = 1;
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);

        KeepManager.getInstance().setKeep(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "关闭Keep");
    }
}
