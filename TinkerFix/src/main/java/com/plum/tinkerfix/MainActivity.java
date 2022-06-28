package com.plum.tinkerfix;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applyPermission();
    }

    public void test(View v) {
        Test test = new Test();
        test.testFix(this);
    }

    public void fix(View v) {
        fixBug();
    }

    private void fixBug() {
        //得到目录就是data/user//包名/app_odex
        File filesDir = this.getDir("odex", Context.MODE_PRIVATE);
        String name = "out.dex";//模拟下载到本地的dex文件，存放在sdcard根目录中
        String filePath = new File(filesDir, name).getAbsolutePath();
        Log.i(TAG, "fixBug,filePath=" + filePath);
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        InputStream is = null;
        FileOutputStream os = null;
        try {
            Log.i(TAG, "fixBug:" + new File(Environment.getExternalStorageDirectory(), name).getAbsolutePath());
            is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), name));
            os = new FileOutputStream(filePath);

            //把下载到本地的dex文件复制到/data/user//包名/app_odex目录下
            int len;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }

            File f = new File(filePath);
            if (f.exists()) {
                Toast.makeText(this, "dex overwrite", Toast.LENGTH_LONG).show();
            }

            FixManager.loadDex(this);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void applyPermission() {
        String permissions[] = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        boolean hasGranted = true;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                hasGranted = false;
                break;
            }
        }

        if (!hasGranted) {//如果没有权限，重新申请权限
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
