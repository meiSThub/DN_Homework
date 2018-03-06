package ubt.com.test.image.helper;

import android.graphics.Color;

/**
 * Created by ubt on 2017/12/21.
 *
 * @description:浮雕效果像素处理算法
 */

public class EmbossPixelsHandle extends ColorPixelsHandle {

    @Override
    public int[] handleColorPixel(int[] source) {
        //不调用父类方法
        if (source == null) return null;
        int newPx[] = new int[source.length];

        for (int i = 1; i < source.length; i++) {
            int preColor = source[i - 1];
            int color = source[i];
            int a = Color.alpha(color);

            int r1 = handlRed(preColor);
            int g1 = handlGreen(preColor);
            int b1 = handlBlue(preColor);

            int r = handlRed(color);
            int g = handlGreen(color);
            int b = handlBlue(color);

            //浮雕效果算法核心
            r = r - r1 + 127;
            g = g - g1 + 127;
            b = b - b1 + 127;
            newPx[i] = handleColorPixel(a, r, g, b);
        }
        newPx[0] = source[0];
        return newPx;
    }


    @Override
    protected int handlAlpha(int color) {
        return Color.alpha(color);
    }

    @Override
    protected int handlRed(int color) {
        return Color.red(color);
    }

    @Override
    protected int handlGreen(int color) {
        return Color.green(color);
    }

    @Override
    protected int handlBlue(int color) {
        return Color.blue(color);
    }
}
