package com.mei.ipc.binderpool;

import android.os.RemoteException;

/**
 * Created by ubt on 2018/2/26.
 * 模拟功能模块二
 *
 * @description:AIDL接口实现
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
