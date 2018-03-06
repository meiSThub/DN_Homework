package ubt.com.test.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ubt on 2017/12/20.
 *
 * @description:
 */

public class ClockView extends View {
    private Paint mPaint;
    private Paint mCirclePaint;
    private int mHeight;
    private int mWidth;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(0xff000000);
        mCirclePaint.setStrokeWidth(10);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xff000000);
        mPaint.setStrokeWidth(10);
        mPaint.setTextSize(30);
        setLayerType(LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mWidth >> 1, mHeight >> 1, mWidth >> 2, mCirclePaint);
        canvas.save();
        canvas.rotate(-60, mWidth >> 1, mHeight >> 1);
        for (int i = 0; i < 12; i++) {
            if (i % 6 == 0) {
                canvas.drawLine(mWidth * 0.75f, mHeight >> 1, mWidth * 0.75f - 60, mHeight >> 1, mPaint);
            } else {
                canvas.drawLine(mWidth * 0.75f, mHeight >> 1, mWidth * 0.75f - 40, mHeight >> 1, mPaint);
            }
            if (i < 10) {
                canvas.drawText(String.valueOf(i + 1), mWidth * 0.75f - 80, mHeight >> 1, mPaint);
            } else {
                canvas.drawText(String.valueOf(i + 1), mWidth * 0.75f - 100, mHeight >> 1, mPaint);
            }
            canvas.rotate(30, mWidth >> 1, mHeight >> 1);
        }
        canvas.restore();

        canvas.drawLine(mWidth >> 1, mHeight >> 1, mWidth * 0.55f, mHeight * 0.55f, mPaint);
        canvas.drawLine(mWidth >> 1, mHeight >> 1, mWidth * 0.52f, mHeight * 0.6f, mPaint);


    }
}
