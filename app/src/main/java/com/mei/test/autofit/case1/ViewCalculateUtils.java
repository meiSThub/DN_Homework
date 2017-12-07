package com.mei.test.autofit.case1;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by mei on 2017/11/25.
 * Description:计算View的真实宽高的工具类
 */

public class ViewCalculateUtils {

    public static void setViewLinearLayoutParams(View view, int width, int height, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != LinearLayout.LayoutParams.MATCH_PARENT || width != LinearLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.getInstance(MyApplication.getInstance()).getWidth(width);
        } else {
            layoutParams.width = width;
        }

        if (height != LinearLayout.LayoutParams.MATCH_PARENT || height != LinearLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = UIUtils.getInstance(MyApplication.getInstance()).getHeight(height);
        } else {
            layoutParams.height = height;
        }
        layoutParams.leftMargin = UIUtils.getInstance(MyApplication.getInstance()).getWidth(leftMargin);
        layoutParams.topMargin = UIUtils.getInstance(MyApplication.getInstance()).getHeight(topMargin);
        layoutParams.rightMargin = UIUtils.getInstance(MyApplication.getInstance()).getWidth(rightMargin);
        layoutParams.bottomMargin = UIUtils.getInstance(MyApplication.getInstance()).getHeight(bottomMargin);
        view.setLayoutParams(layoutParams);
    }
}
