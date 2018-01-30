package com.mei.test.optimize.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.mei.test.R;

/**
 * Bitmap内存优化
 */
public class BitmapMemoryOptimizeActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_memory_optimize);
        /**
         * 注意动态权限
         */


        ImageCache.getInstance().init(this, Environment.getExternalStorageDirectory()+"/dn");

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(this));

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
//        i(bitmap);
//
//
//        Bitmap jpg = BitmapFactory.decodeResource(getResources(), R.drawable.lance_j);
//        i(jpg);
//
//        Bitmap webp = BitmapFactory.decodeResource(getResources(), R.drawable.lance_w);
//        i(webp);


        /**
         * 使bitmap能够被复用
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.lance,options);

        //需要100张图片 加载后一张 前一张图片就不要了
        for (int i =0 ;i< 100;++i){
//            options.inBitmap = bitmap;//使用复用Bitmap
            bitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.lance,options);
        }

    }

    void i(Bitmap bitmap){
        Log.i(TAG,"图片"+bitmap.getWidth()+"x"+bitmap.getHeight()+" 内存大小是:"+bitmap.getByteCount());
    }
}
