package com.mei.test.ui.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mei on 2017/10/24.
 * Description:
 */

public class RegionOpTestView extends View {

    private Paint mPaint;

    public RegionOpTestView(Context context) {
        super(context);
        init();
    }

    public RegionOpTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegionOpTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //绘制直线
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRegin(canvas);
    }

    private Region.Op mOps[] = new Region.Op[]{
            Region.Op.DIFFERENCE,
            Region.Op.INTERSECT,
            Region.Op.UNION,
            Region.Op.XOR,
            Region.Op.REVERSE_DIFFERENCE,
            Region.Op.REPLACE};

    private String mStrOps[] = new String[]{
            "DIFFERENCE",
            "INTERSECT",
            "UNION",
            "XOR",
            "REVERSE_DIFFERENCE",
            "REPLACE"};

    public void drawRegin(Canvas canvas) {
        int y = 0;
        int traX = 0;
        for (int i = 0; i < mOps.length; i++) {
            canvas.save();
            if (i % 2 == 0) {
                traX = 100;
                y++;
            } else {
                traX = 500;
            }

            canvas.translate(traX, 100 + (y - 1) * 400);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLACK);
            Rect rect1 = new Rect(0, 100, 300, 200);
            canvas.drawRect(rect1, mPaint);
            Region region1 = new Region(rect1);

            Rect rect2 = new Rect(100, 0, 200, 300);
            canvas.drawRect(rect2, mPaint);
            Region region2 = new Region(rect2);

            region1.op(region2, mOps[i]);

            RegionIterator interator = new RegionIterator(region1);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.RED);
            Rect rect = new Rect();
            while (interator.next(rect)) {
                canvas.drawRect(rect, mPaint);
            }
            mPaint.setTextSize(40);
            canvas.drawText(mStrOps[i], i == 5 ? 100 : 0, 350, mPaint);
            canvas.restore();
        }
    }

}
