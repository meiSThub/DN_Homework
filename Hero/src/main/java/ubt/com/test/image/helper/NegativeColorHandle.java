package ubt.com.test.image.helper;

import android.graphics.Color;

/**
 * Created by ubt on 2017/12/21.
 *
 * @description:底片像素处理算法
 */

public class NegativeColorHandle extends ColorPixelsHandle {

    @Override
    protected int handlAlpha(int color) {
        return Color.alpha(color);
    }

    @Override
    protected int handlRed(int color) {
        return 255 - Color.red(color);
    }

    @Override
    protected int handlGreen(int color) {
        return 255 - Color.green(color);
    }

    @Override
    protected int handlBlue(int color) {
        return 255 - Color.blue(color);
    }
}
