package com.mei.test.ui.xfermode.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mei.test.R;

/**
 * Created by mei on 2017/6/20.
 * Description:不规则水波纹
 */
public class IrregularWaveView_DSTIN extends View {

    private Bitmap mBitmapWave;
    private Bitmap mBitmapShader;
    private Paint mPaint;
    private int dx;

    public IrregularWaveView_DSTIN(Context context) {
        super(context);
        init();
    }

    public IrregularWaveView_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IrregularWaveView_DSTIN(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mBitmapWave = BitmapFactory.decodeResource(getResources(), R.drawable.wave_bg);
        mBitmapShader = BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画上圆形
        canvas.drawBitmap(mBitmapShader, 0, 0, mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBitmapWave, new Rect(dx, 0, dx + mBitmapWave.getWidth(), mBitmapWave.getHeight()),
                new Rect(0, 0, mBitmapShader.getWidth(), mBitmapShader.getHeight()), mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitmapShader, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    private ValueAnimator animator;

    public void startAnim() {
        dx = 0;
        if (animator != null) {
            animator.cancel();
        }
        animator = ValueAnimator.ofInt(0, mBitmapWave.getWidth());
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            startAnim();
        }
    }
}
