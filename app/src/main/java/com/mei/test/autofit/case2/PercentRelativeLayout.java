package com.mei.test.autofit.case2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mei.test.R;

/**
 * Created by mei on 2017/11/25.
 * Description:自定义布局   百分比布局
 */

public class PercentRelativeLayout extends RelativeLayout {

    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        //测量出子控件的宽高进行改变
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            float widthPercent = 0;
            float heightPercent = 0;
            //把已经得到的布局参数进行更改
            if (layoutParams instanceof PercentRelativeLayout.LayoutParams) {
                //获取到布局文件上的内容
                widthPercent = ((LayoutParams) layoutParams).getWidthPercent();
                heightPercent = ((LayoutParams) layoutParams).getHeightPercent();
            }
            if (widthPercent > 0) {
                layoutParams.width = (int) (width * widthPercent);
            }

            if (heightPercent > 0) {
                layoutParams.height = (int) (height * heightPercent);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //需要把自定义的属性封装进去
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        private float widthPercent;
        private float heightPercent;

        /**
         * 在这里把我们自定义的属性加入
         *
         * @param c
         * @param attrs
         */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            widthPercent = array.getFloat(R.styleable.PercentRelativeLayout_layout_widthPercent, 0f);
            heightPercent = array.getFloat(R.styleable.PercentRelativeLayout_layout_heightPercent, 0f);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public float getWidthPercent() {
            return widthPercent;
        }

        public void setWidthPercent(float widthPercent) {
            this.widthPercent = widthPercent;
        }

        public float getHeightPercent() {
            return heightPercent;
        }

        public void setHeightPercent(float heightPercent) {
            this.heightPercent = heightPercent;
        }
    }
}
