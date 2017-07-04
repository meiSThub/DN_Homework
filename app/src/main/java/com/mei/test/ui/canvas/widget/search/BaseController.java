package com.mei.test.ui.canvas.widget.search;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

/**
 * Created by mei on 2017/7/4.
 * Description:
 */
public abstract class BaseController {
    public static final int STATE_ANIM_NONE = 0;
    public static final int STATE_ANIM_START = 1;
    public static final int STATE_ANIM_STOP = 2;
    public static final int DEFAULT_ANIM_TIME = 5000;
    public static final float DEFAULT_ANIM_STARTF = 0;
    public static final float DEFAULT_ANIM_ENDF = 1;
    private SearchView mSearchView;
    public int mState = STATE_ANIM_NONE;

    public abstract void draw(Canvas canvas, Paint paint);

    public void startAnim() {

    }

    public void resetAnim() {

    }

    public int getWidth() {
        return mSearchView.getWidth();
    }

    public int getHeight() {
        return mSearchView.getHeight();
    }

    public void setSearchView(SearchView mySearchView) {
        this.mSearchView = mySearchView;
    }

    public float mpro = -1;

    public ValueAnimator startViewAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(8000l);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mpro = (float) animation.getAnimatedValue();
                mSearchView.invalidate();
            }
        });

        valueAnimator.start();
        mpro = 0;
        return valueAnimator;
    }

}
