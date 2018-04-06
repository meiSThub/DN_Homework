package com.plum.processkeepalive;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.plum.processkeepalive.activitykeepalive.KeepManager;
import com.plum.processkeepalive.twoprocess.LocalService;
import com.plum.processkeepalive.twoprocess.RemoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();

        //1、通过Activity 提权
//        KeepManager.getInstance().registerKeep(this);

        //2、通过Service 提权
//        startService(new Intent(this, ForgroundService.class));

        //3/Stick 拉活,基本无效
//        startService(new Intent(this,StickService.class));

        //4、账户拉活
//        AccountHelper.addAccount(this);
//        AccountHelper.autoSync();

        //5、JobScheduler 拉活
//        MyJobService.startJob(this);

        //6、双进程守护 保活
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
        com.plum.processkeepalive.twoprocess.MyJobService.startJob(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unregisterKeep(this);
    }

    private void requestPermission() {
        String permissions[] = new String[]{
                Manifest.permission.WRITE_SYNC_SETTINGS,
                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                Manifest.permission.GET_ACCOUNTS
        };
        boolean isGranted = true;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }

        if (!isGranted) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }
}
