package com.mei.test.ui.xfermode.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mei.test.R;

/**
 * Created by mei on 2017/6/20.
 * Description:心电图
 */
public class HeartMap_DSTIN extends View {

    private Bitmap mBitmapHeart;
    private Bitmap mBitmapShader;
    private Paint mPaint;
    private Canvas mShaderCanvas;
    private float mToRightDx;
    private ValueAnimator animator;


    public HeartMap_DSTIN(Context context) {
        super(context);
        init();
    }

    public HeartMap_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartMap_DSTIN(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmapHeart = BitmapFactory.decodeResource(getResources(), R.drawable.heartmap);
        mBitmapShader = Bitmap.createBitmap(mBitmapHeart.getWidth(), mBitmapHeart.getHeight(), Bitmap.Config.ARGB_8888);
        mShaderCanvas = new Canvas(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint.setColor(Color.WHITE);
        Canvas c = new Canvas(mBitmapShader);
        c.drawRect(mBitmapHeart.getWidth() - mToRightDx, 0, mBitmapHeart.getWidth(), mBitmapHeart.getHeight(), mPaint);
        mPaint.reset();

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmapHeart, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitmapShader, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    private void startAnimation() {
        mToRightDx = 0;
        if (animator != null) {
            animator.cancel();
        }
        animator = ValueAnimator.ofFloat(0, mBitmapHeart.getWidth());
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(6000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mToRightDx = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            startAnimation();
        }
    }
}
