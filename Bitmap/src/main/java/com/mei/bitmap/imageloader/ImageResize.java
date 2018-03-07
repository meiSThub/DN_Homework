package com.mei.bitmap.imageloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * Created by ubt on 2018/3/1.
 *
 * @description:
 */

public class ImageResize {

    private static final String TAG = "ImageResize";

    public Bitmap decodeSampleBitmpFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        //计算inSampleSize
        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, reqWidth, reqHeight);
        //加载图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor fd, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    public static int calculateInSampleSize(int originWidth, int originHeight, int targetWidget, int targetHeight) {
        int inSampleSize = 1;
        Log.d("plum", "originWidth=" + originWidth + ";originHeight=" + originHeight + ";targetWidth=" + targetWidget + ";targetHeight=" + targetHeight);
        if (targetWidget == 0 || targetHeight == 0) return 1;

        if (originWidth > targetWidget || originHeight > targetHeight) {
            final int halfWidth = originWidth / 2;
            final int halfHeight = originHeight / 2;
            while ((halfHeight / inSampleSize) >= targetHeight
                    && (halfWidth / inSampleSize) >= targetWidget) {
                inSampleSize *= 2;
            }
        }
        Log.d("plum", "calculateInSampleSize,inSampleSize=" + inSampleSize);
        return inSampleSize;
    }
}
