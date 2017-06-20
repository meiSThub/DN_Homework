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
 * Description:书架高亮效果
 */
public class LightBookView_LIGHTEN extends View {

    private Paint mPaint;
    private Bitmap mBitmap;//书架
    private Bitmap mBitmapLight;//高亮的灯

    public LightBookView_LIGHTEN(Context context) {
        super(context);
        init();
    }

    public LightBookView_LIGHTEN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightBookView_LIGHTEN(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.book_bg);

        mBitmapLight = BitmapFactory.decodeResource(getResources(), R.drawable.book_light);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        canvas.drawBitmap(mBitmapLight, 0, 0, mPaint);
        mPaint.setXfermode(null);
    }
}
