package com.mei.test.ui.path.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mei on 2017/5/23.
 * Description: Path路径相交的模式练习
 */
public class PathFillView extends View {

    private Path mPath;
    private Paint mPaint;

    public PathFillView(Context context) {
        super(context);
        init();
    }

    public PathFillView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathFillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathFillView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextSize(40);
        canvas.drawText("画笔颜色为红色,Path的setFillType方法参数的差异对比", 50, 100, mPaint);
        drawWindingType(canvas);
        drawEvenOddType(canvas);
        drawInverseEvenOdd(canvas);
        drawInverseWinding(canvas);
    }

    private void drawWindingType(Canvas canvas) {
        mPath = new Path();
        mPath.addCircle(200, 300, 100, Path.Direction.CW);
        mPath.addCircle(300, 300, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.WINDING);
        canvas.drawPath(mPath, mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("WINDING模式", 100, 550, mPaint);
        canvas.drawText("取Path所有所在的区域 ", 100, 600, mPaint);
        canvas.drawText("-- 默认的模式 ", 100, 650, mPaint);
    }

    private void drawEvenOddType(Canvas canvas) {
        mPath = new Path();
        mPath.addCircle(600, 300, 100, Path.Direction.CW);
        mPath.addCircle(700, 400, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(mPath, mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("EVEN_ODD模式", 500, 550, mPaint);
        canvas.drawText("取Path所在不相交的区域", 500, 600, mPaint);
    }

    private void drawInverseWinding(Canvas canvas) {
        mPath = new Path();
//        mPath.offset(100, 100);
        mPath.addCircle(200, 800, 100, Path.Direction.CW);
        mPath.addCircle(300, 900, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_WINDING);
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath, mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("INVERSE_WINDING模式", 100, 1100, mPaint);
        canvas.drawText("取path所有未占的区域", 100, 1150, mPaint);
    }


    private void drawInverseEvenOdd(Canvas canvas) {
        mPath = new Path();
        mPath.offset(100, 100);
        mPath.addCircle(600, 800, 100, Path.Direction.CW);
        mPath.addCircle(700, 900, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath, mPaint);
        mPaint.setTextSize(30);
        canvas.drawText("INVERSE_EVEN_ODD模式", 500, 1100, mPaint);
        canvas.drawText("取path所有未占和相交的区域", 500, 1150, mPaint);
    }


}
