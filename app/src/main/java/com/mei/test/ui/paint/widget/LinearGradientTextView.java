package com.mei.test.ui.paint.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/5/28.
 * Description:霓虹灯效果
 */
public class LinearGradientTextView extends TextView {
    private TextPaint mPaint;

    private LinearGradient mLinearGradient;
    private Matrix mMatrix;

    private float mTranslate;
    private float DELTAX = 20;
    private int mGradientSize;//需要颜色渐变效果的文字的长度

    public LinearGradientTextView(Context context) {
        super(context);
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 拿到TextView的画笔
        mPaint = getPaint();
        String text = getText().toString();
        float textWith = mPaint.measureText(text);
        // 3个文字的宽度
        mGradientSize = (int) (textWith / text.length() * 3);

        // 从左边-gradientSize开始，即左边距离文字gradientSize开始渐变
        mLinearGradient = new LinearGradient(-mGradientSize, 0, 0, 0, new int[]{
                0x22ffffff, 0xffffffff, 0x22ffffff}, null, Shader.TileMode.CLAMP
        );

        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTranslate += DELTAX;
        float textWidth = mPaint.measureText(getText().toString());
        textWidth = Math.min(textWidth, getWidth()) + mGradientSize;
        LogUtils.i("textWidth=" + textWidth + ";width=" + getWidth());
        if (mTranslate > textWidth + 1 || mTranslate < 1) {
            DELTAX = -DELTAX;
        }

        mMatrix = new Matrix();
        mMatrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        postInvalidateDelayed(50);

    }
}
