package com.mei.test.ui.path.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mei on 2017/10/25.
 * Description:
 */

public class PathApiView extends View {

    private Paint mPaint;
    private Path mPath;

    public PathApiView(Context context) {
        super(context);
        init();
    }

    public PathApiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathApiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        mPath = new Path();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTextSize(30);
        mPath.close();
        mPath.addCircle(400, 400, 300, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);
        canvas.drawTextOnPath("顺时针方向绘制文字", mPath, 25, 25, mPaint);

        mPath.reset();
        mPath.addCircle(400, 1100, 300, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
        canvas.drawTextOnPath("逆时针方向绘制文字", mPath, 25, 25, mPaint);

    }
}
