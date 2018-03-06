package com.mei.ipc.binderself;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;


import java.util.List;

/**
 * Created by ubt on 2018/2/24.
 *
 * @description:实现Stub类和Stub类中的Proxy代理类
 */

public class BookManagerBinder extends Binder implements IBookManager {

    public BookManagerBinder() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IBookManager asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (iin != null && iin instanceof IBookManager) {
            return (IBookManager) iin;
        }
        return new Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                List<Book> result = getBookList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
            }

            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                Book arg0 = null;
                if (0 != data.readInt()) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                }
                addBook(arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }


    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public List<Book> addBook(Book book) throws RemoteException {
        return null;
    }

    private static class Proxy implements IBookManager {

        private IBinder mRemote;

        Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }

        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            List<Book> result = null;
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getBookList, data, reply, 0);
                reply.readException();
                result = reply.createTypedArrayList(Book.CREATOR);
            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                reply.recycle();
                data.recycle();
            }
            return result;
        }

        @Override
        public List<Book> addBook(Book book) throws RemoteException {

            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                if (book != null) {
                    data.writeInt(1);
                    book.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, data, reply, 0);
                reply.readException();
            } finally {
                reply.recycle();
                data.recycle();
            }
            return null;
        }
    }
}
