package com.mei.test.ui.xfermode.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mei.test.R;

/**
 * Created by mei on 2017/6/19.
 * Description:xfermode 实现橡皮擦效果
 */
public class EraserView_SRCOUT extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mGestureBitmap;
    private Path mPath;//记录手势的路径
    private Canvas mGestureCanvas;
    private Paint mGesturePaint;

    public EraserView_SRCOUT(Context context) {
        super(context);
        init();
    }

    public EraserView_SRCOUT(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EraserView_SRCOUT(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6);
        mPath = new Path();
        mGestureBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mGestureCanvas = new Canvas(mGestureBitmap);
        mGesturePaint = new Paint();
        mGesturePaint.setColor(0xaaffffff);
        mGesturePaint.setStrokeWidth(50);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ////先把手指轨迹画到目标Bitmap上
        mGestureCanvas.drawPath(mPath, mGesturePaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        /*canvas.drawBitmap(mGestureBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);*/

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawBitmap(mGestureBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    private float mStartX;
    private float mStartY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                mPath.moveTo(mStartX, mStartY);
                return true;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = (mStartX + event.getX()) / 2;
                float endY = (mStartY + event.getY()) / 2;
                mPath.quadTo(mStartX, mStartY, endX, endY);
                mStartX = event.getX();
                mStartY = event.getY();
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
