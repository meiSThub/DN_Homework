package ubt.com.test.image.helper;

import android.graphics.Color;

/**
 * Created by ubt on 2017/12/21.
 *
 * @description: 老照片像素处理算法
 */

public class OldPixelsHandle extends ColorPixelsHandle {

    @Override
    protected int handlAlpha(int color) {
        return Color.alpha(color);
    }

    @Override
    protected int handlRed(int color) {
        return (int) (0.393 * Color.red(color) + 0.769 * Color.green(color) + 0.189 * Color.blue(color));
    }

    @Override
    protected int handlGreen(int color) {
        return (int) (0.349 * Color.red(color) + 0.686 * Color.green(color) + 0.168 * Color.blue(color));
    }

    @Override
    protected int handlBlue(int color) {
        return (int) (0.272 * Color.red(color) + 0.534 * Color.green(color) + 0.131 * Color.blue(color));
    }
}
