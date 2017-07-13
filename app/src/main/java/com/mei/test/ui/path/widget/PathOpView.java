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
    private Paint mTextPaint;

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

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(38);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextPaint.setColor(Color.RED);
        canvas.drawText("Path的op方法使用的不同的模式的效果展示,灰色线条只是为", 0, 50, mTextPaint);
        canvas.drawText("了做对比，红色才是使用了相应的模式之后画出来的path路径", 0, 100, mTextPaint);
        mTextPaint.setColor(Color.BLUE);

        drawDifferenceOp(canvas);
        drawIntersectOp(canvas);
        drawUnionOp(canvas);
        drawXOROp(canvas);
        drawReverseDifferenceOp(canvas);
    }

    /**
     * Path.Op.DIFFERENCE :Path1减去Path2后
     *
     * @param canvas
     */
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
        canvas.drawText("Path.Op.DIFFERENCE模式", 0, 500, mTextPaint);
    }

    /**
     * Path.Op.INTERSECT path1和path2共同的部分
     * 交集
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawIntersectOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(700, 300, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(750, 350, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.INTERSECT);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        canvas.drawPath(path1, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(700, 300, 100, mPaint);
        canvas.drawCircle(750, 350, 100, mPaint);
        canvas.drawText("Path.Op.INTERSECT模式", 600, 500, mTextPaint);
    }

    /**
     * 补集
     *
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawUnionOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(300, 700, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(350, 750, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.UNION);

        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        canvas.drawPath(path1, mPaint);

        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(300, 700, 100, mPaint);
        canvas.drawCircle(350, 750, 100, mPaint);

        canvas.drawText("Path.Op.UNION模式", 0, 900, mTextPaint);
    }

    /**
     * 合集，
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawXOROp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(700, 700, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(750, 750, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.XOR);

        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        canvas.drawPath(path1, mPaint);

        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(700, 700, 100, mPaint);
        canvas.drawCircle(750, 750, 100, mPaint);

        canvas.drawText("Path.Op.XOR模式", 600, 900, mTextPaint);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawReverseDifferenceOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(300, 1100, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(350, 1150, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.REVERSE_DIFFERENCE);

        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        canvas.drawPath(path1, mPaint);

        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(300, 1100, 100, mPaint);
        canvas.drawCircle(350, 1150, 100, mPaint);

        canvas.drawText("Path.Op.REVERSE_DIFFERENCE模式", 0, 1300, mTextPaint);
    }
}
