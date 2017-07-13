package com.mei.test.ui.path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mei on 2017/7/13.
 * Description:二阶贝塞尔曲线案例
 */
public class CurveView extends View {

    private float mStartX, mStartY;
    private float mEndX, mEndY;
    private float mContorlX = 400, mContorlY = 100;//默认值
    private Paint mPaint;
    private Path mPath;

    public CurveView(Context context) {
        super(context);
        init();
    }

    public CurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mStartX = 100;
        mStartY = 400;
        mEndX = 1000;
        mEndY = 400;
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartX, mStartY);
        mPath.quadTo(mContorlX, mContorlY, mEndX, mEndY);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(mContorlX, mContorlY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mContorlX = event.getX();
            mContorlY = event.getY();
            invalidate();
        }
        return true;
    }
}
