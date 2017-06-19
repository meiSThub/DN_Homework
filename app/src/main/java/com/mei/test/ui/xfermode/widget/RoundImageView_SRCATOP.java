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
 * Created by mei on 2017/6/19.
 * Description:
 */
public class RoundImageView_SRCATOP extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mShaderBitmap;

    public RoundImageView_SRCATOP(Context context) {
        super(context);
        init();
    }

    public RoundImageView_SRCATOP(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImageView_SRCATOP(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6);
        mShaderBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shade);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        /*canvas.drawBitmap(mShaderBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);*/

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        canvas.drawBitmap(mShaderBitmap, 0, 0, mPaint);

        canvas.restoreToCount(layerId);
    }
}
