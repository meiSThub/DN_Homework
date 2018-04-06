package com.plum.processkeepalive.account;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by mei on 2018/4/6.
 * Description: 同步service，由系统启动
 */

public class SyncService extends Service {

    private static final String TAG = "SyncService";
    private SyncAdapter syncAdapter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /**
         * 系统bindService的时候，拿到我们提供的AbstractThreadedSyncAdapter类型的Binder,
         * 然后调用AbstractThreadedSyncAdapter类型的Binder的onPerformSync方法。
         * 在onPerformSync方法中就可以与互联网 或者 本地数据库同步账户了
         */
        return syncAdapter.getSyncAdapterBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        syncAdapter = new SyncAdapter(getApplicationContext(), true);
    }

    static class SyncAdapter extends AbstractThreadedSyncAdapter {

        public SyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
        }

        @Override
        public void onPerformSync(Account account, Bundle extras, String authority,
                                  ContentProviderClient provider, SyncResult syncResult) {
            Log.e(TAG, "同步账户");
            //与互联网 或者 本地数据库同步账户了

        }
    }
}
