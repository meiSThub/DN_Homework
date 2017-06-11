package com.mei.test.ui.paint.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

/**
 * Created by mei on 2017/6/11.
 * Description:
 */
public class RippleView extends Button {

    private static final int DEFAULT_RADIUS = 50;
    private Paint mPaint;
    private int x, y;//点击的x，y坐标
    private int mRadius;//半径
    private ValueAnimator mAnimator;
    private RadialGradient mGradient;

    public RippleView(Context context) {
        super(context);
        init();
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, mRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (x != event.getX() || y != event.getY()) {
            x = (int) event.getX();
            y = (int) event.getY();
            setRadius(DEFAULT_RADIUS);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
//                startAnimation();
                startAnimation2();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void startAnimation() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        mAnimator = ValueAnimator.ofInt(DEFAULT_RADIUS, (int) Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight()));
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int radius = (int) animation.getAnimatedValue();
                setRadius(radius);
            }
        });
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setRadius(0);
            }
        });
        mAnimator.start();
    }

    private void startAnimation2() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        //因为我们给view提供了一个setRadius的方法，所以直接用ObectAnimator属性动画，让动画自己去执行setRadius方法，
        //从而不需要我们自己设置updateListener去调用setRadius方法了
        mAnimator = ObjectAnimator.ofInt(this, "radius", DEFAULT_RADIUS, (int) Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight()));
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setRadius(0);
            }
        });
        mAnimator.start();
    }

    private void setRadius(int radius) {
        mRadius = radius;
        if (mRadius > 0) {
            mGradient = new RadialGradient(x, y, mRadius, 0x00FFFFFF, 0xFF58FAAC, Shader.TileMode.CLAMP);
            mPaint.setShader(mGradient);
        }
        postInvalidate();
    }
}
