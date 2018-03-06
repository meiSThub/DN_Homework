package ubt.com.test.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

/**
 * Created by ubt on 2017/12/19.
 *
 * @description:
 */

public class FrameLayout extends android.widget.FrameLayout {

    private int mLastX;
    private int mLastY;

    public FrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public FrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("plum", "ViewGroup A dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("plum", "ViewGroup A onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("plum", "ViewGroup A onTouchEvent");
//        scrollByScrollTo(event);
        scrollByScroller(event);
//        return super.onTouchEvent(event);
        return true;
    }

    private int mScrollX;
    private int mScrollY;

    /**
     * 通过系统提供的scrollTo和scrollBy方法实现滚动    滚动的时View的内容
     */
    private void scrollByScrollTo(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("plum", "getScrollX()=" + getScrollX() + ";getScrollY()=" + getScrollY());
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                mScrollX -= offsetX;
                mScrollY -= offsetY;
//                scrollBy(-offsetX, -offsetY);
                scrollTo(mScrollX, mScrollY);
                Log.i("plum", "offsetX=" + offsetX + ";mScrollY=" + mScrollY);
                mLastX = x;//一定要重置，否则offsetX和offsetY会越来越大，在手指没有抬起的时候
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private Scroller mScroller;

    private void init() {
        mScroller = new Scroller(getContext(), new LinearInterpolator());
    }

    private void scrollByScroller(MotionEvent event) {
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
                mScrollX -= offsetX;
                mScrollY -= offsetY;
                scrollBy(-offsetX, -offsetY);
                mLastX = x;//一定要重置，否则offsetX和offsetY会越来越大，在手指没有抬起的时候
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                Log.i("plum", "getScrollX()=" + getScrollX() + ";getScrollY()=" + getScrollY());
                mScroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY());
                mLastX = getScrollX();
                mLastY = getScrollY();
                invalidate();
                break;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            Log.i("plum", "currX=" + mScroller.getCurrX() + ";currY=" + mScroller.getCurrY());
            //方式一
//            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //方式二
//            scrollTo(mScroller.getFinalX() + mScroller.getCurrX(), mScroller.getFinalY() + mScroller.getCurrY());
            //方式三
            scrollBy(mScroller.getCurrX() - mLastX, mScroller.getCurrY() - mLastY);
            mLastX = mScroller.getCurrX();
            mLastY = mScroller.getCurrY();
            invalidate();
        }
    }
}
