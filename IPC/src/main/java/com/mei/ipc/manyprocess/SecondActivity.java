package com.mei.ipc.manyprocess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ubt.ipc.R;
import com.ubt.ipc.manager.UserManager;
import com.ubt.ipc.model.User;
import com.ubt.ipc.utils.MyConstants;
import com.ubt.ipc.utils.MyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("time", System.currentTimeMillis());
                startActivity(intent);
            }
        });
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "UserManage.sUserId=" + UserManager.sUserId);

        recoverFromFile();
    }

    /**
     * 进程二：从文件中恢复进程1保存的对象
     */
    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user;
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH, MyConstants.File_name);
                if (cachedFile.exists()) {
                    ObjectInputStream objectOutputStream = null;
                    try {
                        objectOutputStream = new ObjectInputStream(new FileInputStream(cachedFile));
                        user = (User) objectOutputStream.readObject();
                        Log.d(TAG, "recover user:" + user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        MyUtils.close(objectOutputStream);
                    }
                } else {
                    Log.w(TAG, "文件不存在");
                }
            }
        }).start();
    }


}
