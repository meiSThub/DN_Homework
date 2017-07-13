package com.mei.test.ui.path.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by mei on 2017/7/13.
 * Description: 垃圾桶打开动画
 */
public class GarbageView extends View {

    private Paint mPaint;
    private Path mBodyPath;//垃圾桶身体
    private Path mCapPath;//垃圾桶盖
    private int mDegree;//旋转的角度


    public GarbageView(Context context) {
        super(context);
        // init();
    }

    public GarbageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    public GarbageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

        mBodyPath = new Path();
        //画桶身的路径
        mBodyPath.moveTo(getWidth() * 9 / 24, getHeight() / 8);
        mBodyPath.lineTo(getWidth() * 10 / 24, getHeight() / 4);
        mBodyPath.lineTo(getWidth() * 14 / 24, getHeight() / 4);
        mBodyPath.lineTo(getWidth() * 15 / 24, getHeight() / 8);
        mBodyPath.moveTo(getWidth() * 11 / 24, getHeight() / 8);
        mBodyPath.lineTo(getWidth() * 11 / 24, getHeight() / 4);
        mBodyPath.moveTo(getWidth() * 13 / 24, getHeight() / 8);
        mBodyPath.lineTo(getWidth() * 13 / 24, getHeight() / 4);

        //桶盖路径
        mCapPath = new Path();
        mCapPath.moveTo(getWidth() * 8 / 24, getHeight() / 8);
        mCapPath.lineTo(getWidth() * 16 / 24, getHeight() / 8);
        mCapPath.moveTo(getWidth() * 20 / 48, getHeight() / 8);
        mCapPath.lineTo(getWidth() * 22 / 48, getHeight() / 10);
        mCapPath.lineTo(getWidth() * 26 / 48, getHeight() / 10);
        mCapPath.lineTo(getWidth() * 28 / 48, getHeight() / 8);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mBodyPath, mPaint);
        canvas.save();
        canvas.rotate(mDegree, getWidth() * 15 / 24, getHeight() / 8);
        canvas.drawPath(mCapPath, mPaint);
        canvas.restore();
    }

    public void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 45);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
//        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDegree = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                closeCapAnimation();
            }
        });
        animator.start();
    }

    public void closeCapAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(45, 0);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDegree = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }


}
