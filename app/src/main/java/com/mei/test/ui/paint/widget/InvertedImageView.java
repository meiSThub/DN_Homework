package com.mei.test.ui.paint.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.R;

/**
 * Created by mei on 2017/6/13.
 * Description:
 */
public class InvertedImageView extends View {

    private Bitmap mSourceImg;
    private Bitmap mShadowImg;
    private int bitmapGap = 20;//分割线的高度
    private Paint mPaint;

    public InvertedImageView(Context context) {
        super(context);
        init();
    }

    public InvertedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InvertedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSourceImg = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.xyjy2);
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);//通过矩阵，创建倒影图片
        mShadowImg = Bitmap.createBitmap(mSourceImg, 0, 0, mSourceImg.getWidth(), mSourceImg.getHeight(), matrix, true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        LinearGradient linearGradient = new LinearGradient(0, mSourceImg.getHeight() + bitmapGap, 0,
                mSourceImg.getHeight() * 2 + bitmapGap, 0x80ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把原图片画到画布上
        canvas.drawBitmap(mSourceImg, 0, 0, null);
        //把倒影图片画到画布上
        canvas.drawBitmap(mShadowImg, 0, mSourceImg.getHeight() + bitmapGap, null);
        //在倒影图片上加一层阴影
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, mSourceImg.getHeight() + bitmapGap, mSourceImg.getWidth(), mSourceImg.getHeight() * 2 + bitmapGap, mPaint);
    }
}
