package com.mei.test.ui.flowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.mei.test.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mei on 2017/9/23.
 * Description:流式布局
 */

public class FlowLayout extends ViewGroup {

    /**
     * 用来保存行高的列表
     */
    private List<Integer> mLineHeights = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FlowLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int parentHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int parentLessWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int parentLessHeihtSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        LogUtils.i("plum", "parentLessWidth=" + parentLessWidthSpecSize + ";parentLessHeight=" + parentLessHeihtSpecSize);
        int measureWidth = 0;//测量到的FlowLayout的宽度
        int measureHeight = 0;
        int currLineWidth = 0;//当前行的最大宽度

        if (parentWidthMode == MeasureSpec.EXACTLY && parentHeightMode == MeasureSpec.EXACTLY) {
            measureWidth = parentLessWidthSpecSize;
            measureHeight = parentLessHeihtSpecSize;
            LogUtils.i("plum", "-----确定值------");
        } else {
            int childUsedWidth;
            int childUsedHeight;
            List<View> currLineViews = new ArrayList<>();
            int childCount = getChildCount();
            int line = 0;//行号
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                int currLineHeight = 0;//当前行的最大高度
                if (mLineHeights.size() > line) {
                    currLineHeight = mLineHeights.get(line);
                }

                FlowLayoutParams params = (FlowLayoutParams) child.getLayoutParams();
                childUsedWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                childUsedHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                if (childUsedWidth + currLineWidth <= parentLessWidthSpecSize - getPaddingLeft() - getPaddingRight()) {//位置够，不用换行
                    currLineWidth += childUsedWidth;//当前行的宽度
                    currLineViews.add(child);
                    currLineHeight = Math.max(currLineHeight, childUsedHeight);//当前行的最大高度
                    mLineHeights.add(line, currLineHeight);//当前行的行高
                } else {//换行
                    measureHeight += mLineHeights.get(line);//计算所有行的总高度
                    mLineHeights.add(++line, childUsedHeight);//换行后重置新一行的行高
                    measureWidth = Math.max(measureWidth, currLineWidth);//计算最大宽度

                    currLineWidth = childUsedWidth;
                    currLineViews = new ArrayList<>();
                    currLineViews.add(child);
                }
                params.lineNum = line;
                if (i == childCount - 1) {//如果只有一行或者大于一行时，最后肯定是没有走换行分支的，所以在最后需要把漏的都加上
                    measureHeight += mLineHeights.get(line);//计算所有行的总高度
                    measureWidth = Math.max(measureWidth, currLineWidth);
                }
            }

        }
        measureWidth += getPaddingRight() + getPaddingLeft();
        measureHeight += getPaddingTop() + getPaddingBottom();
        LogUtils.i("plum", "measureWidth=" + measureWidth + ";measureHeight=" + measureHeight);
        // 最终目的
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top, right, bottom;
        int curTop = getPaddingTop();
        int curLeft = getPaddingLeft();

        int count = getChildCount();
        LogUtils.i("plum", "count=" + count);
        int lastLine = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) continue;
            FlowLayoutParams params = (FlowLayoutParams) child.getLayoutParams();
            LogUtils.i("plum", "lastLine=" + lastLine + ";lineNum=" + params.lineNum);
            if (lastLine != params.lineNum) {//需要换行了
                curLeft = getPaddingLeft();
                curTop += mLineHeights.get(lastLine);//上一行的高度
            }
            left = curLeft + params.leftMargin;
            top = curTop + params.topMargin;
            right = left + child.getMeasuredWidth();
            bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);

            lastLine = params.lineNum;
            curLeft += child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
        }
        mLineHeights.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int index);
    }


    void registerItemClickListener() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            final int finalI = i;
            childView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, finalI);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        registerItemClickListener();
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public static class FlowLayoutParams extends MarginLayoutParams {

        public int lineNum;//行号

        public FlowLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }
    }
}
