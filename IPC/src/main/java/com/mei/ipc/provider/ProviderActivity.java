package com.mei.ipc.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ubt.ipc.Book;
import com.ubt.ipc.R;
import com.ubt.ipc.model.User;

public class ProviderActivity extends AppCompatActivity {

    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        //Provider在manifest文件中的authorities属性指定的值
        /*Uri uri = Uri.parse("content://com.ubt.ipc.provider");
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        */
        Uri bookUri = Uri.parse("content://com.ubt.ipc.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(bookCursor.getColumnIndex("_id"));
            book.bookName = bookCursor.getString(bookCursor.getColumnIndex("name"));
            Log.d(TAG, "query book:" + book);
        }
        bookCursor.close();

        Uri userUri = Uri.parse("content://com.ubt.ipc.provider/user");
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.userId = userCursor.getInt(userCursor.getColumnIndex("_id"));
            user.userName = userCursor.getString(userCursor.getColumnIndex("name"));
            user.isMale = userCursor.getInt(userCursor.getColumnIndex("sex")) == 1;
            Log.d(TAG, "query user:" + user);
        }
        userCursor.close();

    }
}
