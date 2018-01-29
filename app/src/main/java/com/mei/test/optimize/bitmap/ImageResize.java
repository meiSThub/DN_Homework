package com.mei.test.optimize.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by mei on 2018/1/29.
 * Description:图片处理类
 */

public class ImageResize {

    /**
     * 按照给定的宽高 缩放bitmap
     *
     * @param context
     * @param resId    图片资源ID
     * @param maxW     需要的图片宽度
     * @param maxH     需要的图片高度
     * @param hasAlpha 图片是否需要透明度
     * @return
     */
    public static Bitmap resizeBitmap(Context context, int resId, int maxW, int maxH, boolean hasAlpha) {
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 只解码出 outxxx参数 比如 宽、高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        //根据宽、高进行缩放
        int w = options.outWidth;
        int h = options.outHeight;
        //设置缩放系数
        options.inSampleSize = calcuteInSampleSize(w, h, maxW, maxH);

        if (!hasAlpha) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    /**
     * 按照给定的宽高 缩放bitmap，并进行内存复用
     *
     * @param context
     * @param resId    图片资源ID
     * @param maxW     需要的图片宽度
     * @param maxH     需要的图片高度
     * @param hasAlpha 图片是否需要透明度
     * @param resuable 被复用的Bitmap，即如果resuable不为null，就可以复用resuable的内存,如果为null，就不复用内存
     * @return
     */
    public static Bitmap resizeBitmap(Context context, int resId, int maxW, int maxH, boolean hasAlpha, Bitmap resuable) {
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 只解码出 outxxx参数 比如 宽、高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        //根据宽、高进行缩放
        int w = options.outWidth;
        int h = options.outHeight;
        //设置缩放系数
        options.inSampleSize = calcuteInSampleSize(w, h, maxW, maxH);

        if (!hasAlpha) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inJustDecodeBounds = false;

        // 需要设置为异变的才能够被复用内存
        options.inMutable = true;
        options.inBitmap = resuable;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    /**
     * 计算缩放系数
     *
     * @param w    图片的原始宽度
     * @param h    图片的原始高度
     * @param maxW 需要的图片宽度
     * @param maxH 需要的图片高度
     * @return 缩放的系数
     */
    private static int calcuteInSampleSize(int w, int h, int maxW, int maxH) {
        int inSampleSize = 1;
        if (w > maxW && h > maxH) {
            inSampleSize = 2;
            //循环 使宽、高小于 最大的宽、高
            while (w / inSampleSize > maxW && h / inSampleSize > maxH) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
