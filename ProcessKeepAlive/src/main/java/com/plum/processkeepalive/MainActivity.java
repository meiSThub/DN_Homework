package com.plum.processkeepalive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.plum.processkeepalive.account.AccountHelper;
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

        //3/Stick 拉活,基本无效
//        startService(new Intent(this,StickService.class));

        //4、账户拉活
        AccountHelper.addAccount(this);
        AccountHelper.autoSync();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unregisterKeep(this);
    }
}
