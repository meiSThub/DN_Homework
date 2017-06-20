package com.mei.test.ui.xfermode.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.R;
import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/6/19.
 * Description:通过xfermode  dstin模式实现倒影图片
 */
public class InvertedImageView_DSTIN extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mInvertBitmap;
    private Bitmap mShaderBitmap;

    public InvertedImageView_DSTIN(Context context) {
        super(context);
        init();
    }

    public InvertedImageView_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InvertedImageView_DSTIN(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6);

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        mInvertBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, false);
        mShaderBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.invert_shade);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.i("mei", "canvas----------------");
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null);

        canvas.drawBitmap(mInvertBitmap, 0, mBitmap.getHeight() + 10, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mShaderBitmap, 0, mBitmap.getHeight() + 10, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
