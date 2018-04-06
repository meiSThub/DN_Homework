package com.plum.processkeepalive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.plum.processkeepalive.activitykeepalive.KeepManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1、通过Activity 提权
        KeepManager.getInstance().registerKeep(this);

        //2、通过Service 提权
//        startService(new Intent(this, ForgroundService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unregisterKeep(this);
    }
}
