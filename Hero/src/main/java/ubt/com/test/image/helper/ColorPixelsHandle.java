package ubt.com.test.image.helper;

import android.graphics.Color;

/**
 * Created by ubt on 2017/12/21.
 *
 * @description:
 */

public abstract class ColorPixelsHandle {

    /**
     * 处理像素
     *
     * @param source 源像素
     */
    public int[] handleColorPixel(int[] source) {
        if (source == null) return null;
        int newPx[] = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            newPx[i] = handleColorPixel(source[i]);
        }
        return newPx;
    }

    protected int handleColorPixel(int color) {
        int a = handlAlpha(color);
        int r = handlRed(color);
        int g = handlGreen(color);
        int b = handlBlue(color);
        return handleColorPixel(a, r, g, b);
    }

    protected int handleColorPixel(int alpha, int red, int green, int blue) {
        return Color.argb(check(alpha), check(red), check(green), check(blue));
    }

    protected abstract int handlAlpha(int color);

    protected abstract int handlRed(int color);

    protected abstract int handlGreen(int color);

    protected abstract int handlBlue(int color);

    /**
     * 检查像素值是否合法
     *
     * @param color
     * @return
     */
    protected int check(int color) {
        if (color > 255) {
            color = 255;
        } else if (color < 0) {
            color = 0;
        }
        return color;
    }

}
