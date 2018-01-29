package com.mei.test.optimize.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mei.test.R;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 999;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bitmap_memory_optimize, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //模拟从互联网 获取的 头像 没有优化
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
// R.drawable.lance);
        //第一次优化     进行图片压缩
//        Bitmap bitmap = ImageResize.resizeBitmap(context, R.drawable.lance,
//                80, 80, false);
        //第二次优化 加入内存，磁盘缓存
        /**
         * 先从 内存缓存中查找图片 如果没找到从复用池查找允许复用的图片
         * 然后使用复用的bitmap inBitmap 去加载新的图片
         */
        Bitmap bitmap = ImageCache.getInstance().getBitmapFromMemory(String.valueOf(position));
        Log.i("Adapter", "从内存缓存获取:" + bitmap);
        if (null == bitmap) {
            //内存没找到 从复用池找可以被复用内存的图片
            Bitmap reusable = ImageCache.getInstance().getReusable(60, 60, 1);
            //从磁盘获取
            bitmap = ImageCache.getInstance().getBitmapFromDisk(String.valueOf(position), reusable);
            Log.i("Adapter", "从磁盘缓存获取:" + bitmap);
            //再重新 下载
            if (null == bitmap) {
                Log.i("Adapter", "复用内存:" + reusable);
                bitmap = ImageResize.resizeBitmap(context, R.drawable.lance,
                        80, 80, false, reusable);
                //新下载的图片加入到内存缓存中
                ImageCache.getInstance().putBitmap2Memory(String.valueOf(position), bitmap);
                //新下载的图片加入到磁盘缓存中
                ImageCache.getInstance().putBitMap2Disk(String.valueOf(position), bitmap);
            }
        }
        holder.iv.setImageBitmap(bitmap);
        return convertView;
    }

    class ViewHolder {
        ImageView iv;

        ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.iv);
        }
    }
}
