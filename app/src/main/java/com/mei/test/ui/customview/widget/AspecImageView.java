package com.mei.test.ui.customview.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by mei on 2017/7/20.
 * Description:组件大小与内容一致的ImageView
 */
public class AspecImageView extends ImageView {

    public AspecImageView(Context context) {
        this(context, null);
    }

    public AspecImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AspecImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredSizeWidth;
        float aspect;//图片宽高的比值

        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        desiredSizeWidth = drawable.getIntrinsicWidth();//图片的宽
        aspect = drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();

        int width;
        {
            int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
            switch (widthSpecMode) {
                case MeasureSpec.AT_MOST:
                    width = Math.min(desiredSizeWidth, widthSpecSize);
                    break;
                case MeasureSpec.EXACTLY:
                    width = widthSpecSize;
                    break;
                case MeasureSpec.UNSPECIFIED:
                default:
                    width = desiredSizeWidth;
                    break;
            }
        }
        //上面的代码块实现的功能，其实在View中已经提供了一个静态方法，可以直接调用,如下：
        width = View.resolveSize(desiredSizeWidth, widthMeasureSpec);

        int height = (int) (width / aspect);//高度的期望值
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.EXACTLY) {
            if (height > heightSpecSize) {
                height = heightSpecSize;
                width = (int) (height * aspect);
            }
        }
        setMeasuredDimension(width, height);
    }
}
