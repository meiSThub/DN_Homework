package com.mei.ipc.aidl;

import com.mei.ipc.aidl.Book;


/**
* 在有新书被添加的时候的回调接口
*/
interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
