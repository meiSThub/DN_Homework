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
 * Description:
 */
public class PathOpView extends View {

    private Paint mPaint;

    public PathOpView(Context context) {
        super(context);
        init();
    }

    public PathOpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathOpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PathOpView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDifferenceOp(canvas);
        drawIntersectOp(canvas);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawDifferenceOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(300, 300, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(350, 350, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(300, 300, 100, mPaint);
        canvas.drawCircle(350, 350, 100, mPaint);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawIntersectOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(700, 300, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(750, 350, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.INTERSECT);
        canvas.drawPath(path1,mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(700,300,100,mPaint);
        canvas.drawCircle(750,350,100,mPaint);
    }
}
