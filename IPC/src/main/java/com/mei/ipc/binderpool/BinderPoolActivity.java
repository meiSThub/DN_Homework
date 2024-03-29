package com.mei.ipc.binderpool;

import android.os.IBinder;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ubt.ipc.R;

public class BinderPoolActivity extends AppCompatActivity {

    private static final String TAG = "BinderPoolActivity";
    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        new Thread(new Runnable() {

            @Override
            public void run() {
                doWork();
            }
        }).start();
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInstance(this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
        Log.d(TAG, "visit ISecurityCenter");

        String msg = "helloworld-安卓";
        System.out.println("content:" + msg);

        try {
            String password = mSecurityCenter.encrypt(msg);
            System.out.println("encrypt:" + password);
            System.out.println("descrypt:" + mSecurityCenter.decrypt(password));


        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computeBinder);
        try {
            System.out.println("3+5=" + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
