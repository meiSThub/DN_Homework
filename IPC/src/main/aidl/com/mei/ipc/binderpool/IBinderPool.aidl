// IBinderPool.aidl
package com.mei.ipc.binderpool;

// Declare any non-default types here with import statements
//binder连接池接口
interface IBinderPool {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    IBinder queryBinder(int binderCode);
}
