package com.mei.test.ui.canvas.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/6/29.
 * Description:自定义横向滚动的view
 */
public class GallaryHorizonalScrollView extends HorizontalScrollView implements View.OnTouchListener {
    private static final String TAG = "GallaryHorizonalScrollView";
    private LinearLayout container;
    private int centerX;
    private int icon_width;

    public GallaryHorizonalScrollView(Context context) {
        super(context);
        init();
    }

    public GallaryHorizonalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GallaryHorizonalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //在ScrollView里面放置一个水平线性布局，再往里面放置很多ImageView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        container = new LinearLayout(getContext());
        container.setLayoutParams(params);
        setOnTouchListener(this);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View v = container.getChildAt(0);
        icon_width = v.getWidth();
        LogUtils.i(TAG, "icon_width=" + icon_width);

        //得到中间x坐标

        centerX = getWidth() / 2;

        LogUtils.i(TAG, "centerx=" + centerX);
        //处理下，中心坐标改为中心图片的左边界

        centerX = centerX - icon_width / 2;
        //给LinearLayout和hzv之间设置左边框距离
        container.setPadding(centerX, 0, centerX, 0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //渐变图片
            reveal();
        }
        return false;
    }

    private void reveal() {
        //渐变效果
        //得到hzv滑出去的距离
        int scrollX = getScrollX();
        LogUtils.i(TAG, "scrollX=" + scrollX);
        //找到两张渐变的图片的下标--左，右
        int indexLeft = scrollX / icon_width;
        int indexRight = indexLeft + 1;
        //设置图片的Level
        for (int i = 0; i < container.getChildCount(); i++) {
            if (i == indexLeft || i == indexRight) {
                //变化比例
                float ratio = 5000f / icon_width;
                ImageView imgLeft = (ImageView) container.getChildAt(indexLeft);
                //scrollX%icon_width：代表滑出去的距离
                //滑出去了icon_width/2  icon_width/2%icon_widht
                imgLeft.setImageLevel((int) (5000 - scrollX % icon_width * ratio));
                //右边
                if (indexRight < container.getChildCount()) {
                    ImageView imgRight = (ImageView) container.getChildAt(indexRight);
                    //scrollX%icon_width:代表滑出去的距离
                    //滑出去了icon_width/2  icon_width/2%icon_width
                    imgRight.setImageLevel((int) (10000 - scrollX % icon_width * ratio));
                }
            } else {
                ImageView img = (ImageView) container.getChildAt(i);
                img.setImageLevel(0);
            }
        }
    }

    //添加图片的方法
    public void addImageViews(Drawable[] revealDrawables) {
        for (int i = 0; i < revealDrawables.length; i++) {
            ImageView img = new ImageView(getContext());
            img.setImageDrawable(revealDrawables[i]);
            container.addView(img);
            if (i == 0) {
                img.setImageLevel(5000);
            }
        }
        addView(container);
    }
}
