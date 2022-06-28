package com.mei.bitmap.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import android.util.AttributeSet;

/**
 * Created by ubt on 2018/3/2.
 *
 * @description:正方形ImageView
 */

public class SquareImageView extends AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽高一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
