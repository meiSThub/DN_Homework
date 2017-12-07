package com.mei.test.autofit.case1;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by mei on 2017/11/25.
 * Description:
 */

public class UIUtils {

    private static UIUtils mInstance;

    /**
     * 基准宽高,以这个宽高为基准，与其他的分辨率相比算出比例，从而计算出实际像素值
     */
    public static final float STANDARD_WIDTH = 1080.0f;
    public static final float STANDARD_HEIGHT = 1872.0f;//1920-48=1872；48为默认状态栏的高度
    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";//状态栏的高度在这个类中可以得到

    //实际设备分辨率
    public float displayMetricsWidth;
    public float displayMetricsHeight;


    public static UIUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UIUtils.class) {
                if (mInstance == null) {
                    mInstance = new UIUtils(context);
                }
            }
        }
        return mInstance;
    }

    //初始化设备的真实宽高  高度是减去了状态栏之后的高度
    private UIUtils(Context context) {
        //获取屏幕的真实宽高
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            manager.getDefaultDisplay().getMetrics(displayMetrics);
            //获取到状态栏的高度   默认为48
            int systemBarHeight = getSystemBarHeight(context);
            //处理真实宽高的问题
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {//横屏
                this.displayMetricsWidth = (float) displayMetrics.heightPixels;
                this.displayMetricsHeight = (float) displayMetrics.widthPixels - systemBarHeight;
            } else {//竖屏
                this.displayMetricsWidth = (float) displayMetrics.widthPixels;
                this.displayMetricsHeight = (float) displayMetrics.heightPixels - systemBarHeight;
            }
        }
    }

    private int getSystemBarHeight(Context context) {
        return getValue(context, DIMEN_CLASS, "system_bar_height", 48);
    }

    /**
     * 获取类中指定的属性的值
     *
     * @param context
     * @param attrGroupClass 类全名  安卓源码中找到存放维度的类
     * @param attrName       属性名：状态栏的高度
     * @param defaultHeight  状态栏的默认高度：48
     * @return
     */
    private int getValue(Context context, String attrGroupClass, String attrName, int defaultHeight) {
        try {
            Class e = Class.forName(attrGroupClass);
            Object obj = e.newInstance();
            Field field = e.getField(attrName);
            //获取到的是一个Id
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultHeight;
        }
    }

    /**
     * 获取缩放以后的结果
     *
     * @param width
     * @return
     */
    public float getWidth(float width) {
        return width * displayMetricsWidth / STANDARD_WIDTH;
    }

    public float getHeight(float height) {
        return height * displayMetricsHeight / STANDARD_HEIGHT;
    }


    public int getWidth(int width) {
        return (int) (width * displayMetricsWidth / STANDARD_WIDTH);
    }

    public int getHeight(int height) {
        return (int) (height * displayMetricsHeight / STANDARD_HEIGHT);
    }

}
