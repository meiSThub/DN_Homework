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
 * Created by mei on 2017/6/20.
 * Description:实现推特小鸟边缘描色
 */
public class TwitterView_MULTIPLY extends View {

    private Bitmap mBitmapBlue;
    private Bitmap mBitmapWhite;
    private Paint mPaint;

    public TwitterView_MULTIPLY(Context context) {
        super(context);
        init();
    }

    public TwitterView_MULTIPLY(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmapBlue = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_bg);
        mBitmapWhite = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_light);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBitmapWhite, 100, 100, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(mBitmapBlue, 100, 100, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}
