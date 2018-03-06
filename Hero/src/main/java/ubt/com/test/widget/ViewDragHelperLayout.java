package ubt.com.test.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ubt on 2017/12/20.
 * 借助ViewDragHelper类，模拟实现类似QQ的侧滑效果
 *
 * @description:
 */

public class ViewDragHelperLayout extends android.widget.FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mMainView;
    private View mMenuView;
    private int mWidth;
    private int mOriginX;

    public ViewDragHelperLayout(Context context) {
        super(context);
        init();
    }

    public ViewDragHelperLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewDragHelperLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, mCallback);
    }

    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.i("plum", "child=" + child);
            return child == mMenuView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.i("plum", "left=" + left + ";dx=" + dx + ";target.getLeft()=" + child.getLeft());
            if (child.getLeft() > 0 || child.getRight() < 30) {
                left = 0;
            }
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {//默认返回0
            Log.i("plum", "top=" + top + ";dy=" + dy);
            return super.clampViewPositionVertical(child, top, dy);//侧滑时，垂直方向不滚动，
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓慢移动到指定位置
            if (mMenuView == null) return;
            if (mMenuView.getLeft() >= 0) {
                //相当于Scroller的startScroll方法
                mViewDragHelper.smoothSlideViewTo(mMenuView, mOriginX, 0);
                ViewCompat.postInvalidateOnAnimation(ViewDragHelperLayout.this);
            }
        }
    };

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMainView = getChildAt(0);
        mMenuView = getChildAt(1);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mOriginX = mMenuView.getLeft();
        Log.i("plum", "mOriginX=" + mOriginX);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将事件委托给ViewDragHelper类处理
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
