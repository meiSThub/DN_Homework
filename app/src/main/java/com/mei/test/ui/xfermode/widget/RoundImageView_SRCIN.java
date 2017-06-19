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
public class RoundImageView_SRCIN extends View {
    private Paint mPaint;
    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;

    public RoundImageView_SRCIN(Context context) {
        super(context);
        init();
    }

    public RoundImageView_SRCIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.shade);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBitmapDst, 10, 10, mPaint);

//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmapSrc, 10, 10, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
