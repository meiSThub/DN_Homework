package com.mei.test.autofit.case3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mei on 2017/11/25.
 * Description:
 */

public class RelativeLayout extends android.widget.RelativeLayout {
    public RelativeLayout(Context context) {
        super(context);
    }

    public RelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            if (params instanceof RelativeLayout.LayoutParams) {
                /*if (params.width != LayoutParams.WRAP_CONTENT || params.width != LayoutParams.MATCH_PARENT
                        && params.width == ((LayoutParams) params).fitWidth) {
                    params.width = ScreenParamsUtils.getInstance(getContext()).getFitWidth(((LayoutParams) params).fitWidth);
                }
                if (params.height != LayoutParams.WRAP_CONTENT || params.height != LayoutParams.MATCH_PARENT &&
                        params.height == ((LayoutParams) params).fitHeight) {
                    params.height = ScreenParamsUtils.getInstance(getContext()).getFitHeight(((LayoutParams) params).fitHeight);
                }*/
                ((LayoutParams) params).resetWidthAndHeight(getContext());
                ((LayoutParams) params).resetMargin(getContext());
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends android.widget.RelativeLayout.LayoutParams {

        private int fitWidth;
        private int fitHeight;
        private int fitLeftMargin;
        private int fitTopMargin;
        private int fitRightMargin;
        private int fitBottomMargin;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            fitWidth = width;
            fitHeight = height;
            fitLeftMargin = leftMargin;
            fitTopMargin = topMargin;
            fitRightMargin = rightMargin;
            fitBottomMargin = bottomMargin;
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public void resetWidthAndHeight(Context context) {
            if ((width != LayoutParams.WRAP_CONTENT || width != LayoutParams.MATCH_PARENT) && width == fitWidth) {
                width = ScreenParamsUtils.getInstance(context).getFitWidth(fitWidth);
            }
            if ((height != LayoutParams.WRAP_CONTENT || height != LayoutParams.MATCH_PARENT) && height == fitHeight) {
                height = ScreenParamsUtils.getInstance(context).getFitHeight(fitHeight);
            }
        }

        public void resetMargin(Context context) {
            if (leftMargin != 0 && leftMargin == fitLeftMargin) {
                leftMargin = ScreenParamsUtils.getInstance(context).getFitWidth(fitLeftMargin);
            }
            if (topMargin != 0 && topMargin == fitTopMargin) {
                topMargin = ScreenParamsUtils.getInstance(context).getFitWidth(fitTopMargin);
            }
            if (rightMargin != 0 && rightMargin == fitRightMargin) {
                rightMargin = ScreenParamsUtils.getInstance(context).getFitWidth(fitRightMargin);
            }
            if (bottomMargin != 0 && bottomMargin == fitBottomMargin) {
                bottomMargin = ScreenParamsUtils.getInstance(context).getFitWidth(fitBottomMargin);
            }
        }


    }
}
