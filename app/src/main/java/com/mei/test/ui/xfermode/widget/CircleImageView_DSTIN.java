package com.mei.test.ui.xfermode.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.R;

/**
 * Created by mei on 2017/6/14.
 * Description:通过xfermode实现圆角图片
 */
public class CircleImageView_DSTIN extends View {
    private Paint mPaint;
    private Bitmap mBitmapShader;
    private Bitmap mBitmapSrc;

    public CircleImageView_DSTIN(Context context) {
        super(context);
        init();
    }

    public CircleImageView_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmapShader = BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6);
        mBitmapSrc = Bitmap.createBitmap(mBitmapSrc, 0, 0, mBitmapShader.getWidth(), mBitmapShader.getHeight(), null, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmapSrc, 10, 10, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitmapShader, 10, 10, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
