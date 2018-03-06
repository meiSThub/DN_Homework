package com.mei.ipc.binderpool;

import android.os.RemoteException;

/**
 * Created by ubt on 2018/2/26.
 * 模拟功能模块一
 *
 * @description:AIDL接口实现
 */

public class SecurityCenterImpl extends ISecurityCenter.Stub {

    private static final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
