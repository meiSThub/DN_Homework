package com.plum.processkeepalive.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

public class AccountHelper {

    private static final String TAG = "AccountHelper";
    private static final String ACCOUNT_TYPE = "com.plum.processkeepalive.account";

    public static void addAccount(Context context) {
        AccountManager am = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        //获得此类型的账户
        Account[] accounts = am.getAccountsByType(ACCOUNT_TYPE);

        if (accounts.length > 0) {
            Log.e(TAG, "账户已经存在");
            return;
        }

        //给这个账户类型添加一个账户
        Account plum = new Account("plumax", ACCOUNT_TYPE);
        am.addAccountExplicitly(plum, "plum", new Bundle());

    }

    /**
     * 设置账户自动同步
     */
    public static void autoSync() {
        Account plum = new Account("plumax", ACCOUNT_TYPE);
        //设置同步
        ContentResolver.setIsSyncable(plum, "com.plum.processkeepalive.account.provider", 1);
        //设置自动同步
        ContentResolver.setSyncAutomatically(plum, "com.plum.processkeepalive.account.provider", true);
        //设置同步周期
        ContentResolver.addPeriodicSync(plum, "com.plum.processkeepalive.account.provider",
                new Bundle(), 1);
    }

}
