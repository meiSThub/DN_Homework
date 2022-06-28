package com.mei.ipc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * Created by ubt on 2018/2/26.
 *
 * @description:
 */

public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider";

    public static final String AUTHORITY = "com.ubt.ipc.provider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");//对应book表的uri
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");//对应user表的uri

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMather = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * 我们分别为book表和user表指定了Uri，分别为"content://com.ubt.ipc.provider/book"和"content://com.ubt.ipc.provider/user"
     * 这两个Uri所关联的Uri_Code分别为0和1，关联过程由静态代码块完成。
     */
    static {
        sUriMather.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMather.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMather.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TALBE_NAME;
                break;
        }
        return tableName;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate,current thread:" + Thread.currentThread().getName());
        mContext = getContext();
        //ContentProvider创建时，初始化数据库
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("delete from " + DbOpenHelper.USER_TALBE_NAME);
        mDb.execSQL("insert into book values(3,'Android');");
        mDb.execSQL("insert into book values(4,'Ios');");
        mDb.execSQL("insert into book values(5,'Html5');");
        mDb.execSQL("insert into user values(1,'jake',1);");
        mDb.execSQL("insert into user values(2,'jasmine',0);");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query,current thread:" + Thread.currentThread().getName());

        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType,current thread:" + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "onCreate,current thread:" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        mDb.insert(table, null, values);
        //插入数据的时候，引起数据源的改变，这个时候我们需要通过ContentResolver的notifyChange方法来通知外界
        //当前ContentProvider中的数据已经发生改变。
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete,current thread:" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update,current thread:" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
