package com.mei.ipc.binderself;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;


import java.util.List;

/**
 * Created by ubt on 2018/2/23.
 *
 * @description:
 */

public interface IBookManager extends IInterface {

    /**
     * Binder的唯一标识，一般 用当前Binder的类名标识
     */
    static final String DESCRIPTOR = "com.ubt.ipc.binderself.IBookManager";

    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;

    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList() throws RemoteException;

    public List<Book> addBook(Book book) throws RemoteException;
}
