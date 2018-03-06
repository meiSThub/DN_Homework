package ubt.com.test.project.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by ubt on 2018/1/4.
 *
 * @description:
 */

public class ScreenUtils {

    /**
     * 获取屏幕相关参数
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenSize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取屏幕密度 density
     *
     * @param context
     * @return
     */
    public static float getDeviceDensity(Context context) {
        DisplayMetrics metrics = getScreenSize(context);
        return metrics.density;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static float getScreenWidth(Context context) {
        DisplayMetrics metrics = getScreenSize(context);
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static float getScreenHeight(Context context) {
        DisplayMetrics metrics = getScreenSize(context);
        return metrics.heightPixels;
    }
}
