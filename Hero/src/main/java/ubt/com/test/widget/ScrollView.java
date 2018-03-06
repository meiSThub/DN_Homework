package ubt.com.test.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by ubt on 2017/12/18.
 *
 * @description:
 */

public class ScrollView extends ViewGroup {
    private int mLastY;
    private int mStart;
    private Scroller mScroller;
    private int height;
    private int mEnd;
    private int mLastX;

    public ScrollView(Context context) {
        super(context);
        init();
    }

    public ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        height = getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(height, hSize * count);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(0, height * i, getMeasuredWidth(), height * (i + 1));
            }
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("plum", "ViewGroup B dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("plum", "ViewGroup B onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("plum", "ViewGroup B onTouchEvent");
//        scrollByLayout(event);
        scrollByScrollTo(event);
        /*int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (getScrollY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - height) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if (dScrollY > 0) {
                    if (dScrollY < height / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, height - dScrollY);
                    }
                } else {
                    if (-dScrollY < height / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -height - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();*/
        return true;
    }

    /******
     *实现滑动的7中方式:
     */

    /**
     * 1、layout方法实现滑动   滑动的时View本身
     */
    private void scrollByLayout(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    /**
     * 2、通过offsetLeftAndRight()和offsetTopAndBottom()方法实现滑动      滑动的时View本身
     *
     * @param event
     */
    private void scrollByOffsetLeftAndRight(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    /**
     * 3、通过动态修改View的margins属性来实现滑动   滑动的时View本身
     *
     * @param event
     */
    private void scrollByLayoutParams(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
                params.leftMargin = getLeft() + offsetX;
                params.topMargin = getTop() + offsetY;
                setLayoutParams(params);
                Log.i("plum", "offsetX=" + offsetX);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    /**
     * 通过系统提供的scrollTo和scrollBy方法实现滚动    滚动的时View的内容
     */
    private void scrollByScrollTo(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                scrollBy(offsetX, offsetY);
                Log.i("plum", "offsetX=" + offsetX);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

}
