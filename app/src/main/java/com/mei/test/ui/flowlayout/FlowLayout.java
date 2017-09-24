package com.mei.test.ui.flowlayout;

import android.content.Context;
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
     * 用来保存每行views的列表
     */
    private List<List<View>> mViewLinesList = new ArrayList<>();
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

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
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
        int currLineHeight = 0;//当前行的最大高度
        if (parentWidthMode == MeasureSpec.EXACTLY && parentHeightMode == MeasureSpec.EXACTLY) {
            measureWidth = parentLessWidthSpecSize;
            measureHeight = parentLessHeihtSpecSize;
            LogUtils.i("plum","-----确定值------");
        } else {
            int childUsedWidth;
            int childUsedHeight;
            List<View> viewList = new ArrayList<>();
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);//测量子View
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                childUsedWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                childUsedHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                if (currLineWidth + childUsedWidth > parentLessWidthSpecSize) {//说明需要换行了
                    /**1、记录当前行的信息***/

                    //1、记录当前行的最大宽度，高度累加
                    measureWidth = Math.max(measureWidth, currLineWidth);
                    measureHeight += currLineHeight;
                    //2、将当前行的viewList添加至总的mViewsList，将行高添加至总的行高List
                    mViewLinesList.add(viewList);
                    mLineHeights.add(currLineHeight);

                    //1、重新赋值新一行的宽、高
                    currLineWidth = childUsedWidth;
                    currLineHeight = childUsedHeight;
                    // 2、新建一行的viewlist，添加新一行的view
                    viewList = new ArrayList<>();
                    viewList.add(child);
                } else {
                    // 记录某行内的消息
                    //1、行内宽度的叠加、高度比较
                    currLineWidth += childUsedWidth;
                    currLineHeight = Math.max(currLineHeight, childUsedHeight);
                    // 2、添加至当前行的viewList中
                    viewList.add(child);
                }
                /*****3、如果正好是最后一行需要换行**********/
                if (i == childCount - 1) {
                    //1、记录当前行的最大宽度，高度累加
                    measureWidth = Math.max(measureWidth, currLineWidth);
                    measureHeight += currLineHeight;
                    //2、将当前行的viewList添加至总的mViewsList，将行高添加至总的行高List
                    mViewLinesList.add(viewList);
                    mLineHeights.add(currLineHeight);
                }
            }
        }
        LogUtils.i("plum", "measureWidth=" + measureWidth + ";measureHeight=" + measureHeight);
        // 最终目的
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top, right, bottom;
        int curTop = 0;
        int curLeft = 0;
        int lineCount = mViewLinesList.size();
        for (int i = 0; i < lineCount; i++) {
            List<View> viewList = mViewLinesList.get(i);
            int lineViewSize = viewList.size();
            for (int j = 0; j < lineViewSize; j++) {
                View child = viewList.get(j);
                MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
                left = curLeft + params.leftMargin;
                top = curTop + params.topMargin;
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                curLeft += child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            }
            curLeft = 0;
            curTop += mLineHeights.get(i);
        }
        mViewLinesList.clear();
        mLineHeights.clear();
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
}
