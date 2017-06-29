package com.mei.test.ui.canvas.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/6/29.
 * Description:图片拼接高级实现
 */
public class RevealDrawable2 extends Drawable {

    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;

    //灰色图
    private Drawable mUnselectedDrawable;
    //彩色图
    private Drawable mSelectedDrawable;

    private Rect mTmpRect = new Rect();
    private int mOrientation;

    public RevealDrawable2(Drawable unselectedDrawable, Drawable selectedDrawable) {
        this(unselectedDrawable, selectedDrawable, HORIZONTAL);
    }

    public RevealDrawable2(Drawable unselectedDrawable, Drawable selectedDrawable, int orientation) {
        mUnselectedDrawable = unselectedDrawable;
        mSelectedDrawable = selectedDrawable;
        mOrientation = orientation;
    }

    /**
     * 在draw方法里面将两张图片进行剪裁和拼接
     * <p/>
     * <p/>
     * Drawable 状态，可以根据level来切换不同的状态绘制，level值是从0 ~ 10000进行变化
     * 1）全灰色  --- 0 或者 10000
     * <p/>
     * 2）全彩色  --- 5000
     * <p/>
     * 3）左边灰色，右边彩色  5000 ~ 0
     * <p/>
     * 4）左边彩色，右边灰色 10000 ~ 5000
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        LogUtils.i("draw--------------");

        int level = getLevel();
        if (level == 0 || level == 1000) {
            //画整张灰色的图
            mUnselectedDrawable.draw(canvas);
        } else if (level == 5000) {
            //画整张彩色的图，即高亮的图
            mSelectedDrawable.draw(canvas);
        } else {//左边灰色右边彩色
            Rect bounds = getBounds();
            Rect r = mTmpRect;

            //灰色与彩色图片的占比,取值范围设定为：-1~1，-1~0表示左边灰色,右边彩色，0~1表示左边是彩色的，右边灰色
            float ratio = (level / 5000f) - 1f;
            //1、画左边灰色
            {
                int w = bounds.width();
                int h = bounds.height();
                if (mOrientation == HORIZONTAL) {
                    w = (int) (w * Math.abs(ratio));
                }
                if (mOrientation == VERTICAL) {
                    h = (int) (h * Math.abs(ratio));
                }
                int gravity = ratio < 0 ? Gravity.LEFT : Gravity.RIGHT;
                Gravity.apply(
                        gravity,//从哪个方向开始剪裁图片,如：从左边开始裁剪，从右边开始裁剪
                        w,      //裁剪图片的宽度,目标矩形的宽
                        h,      //裁剪图片的高度，目标矩形的高
                        bounds, //被裁剪的图片的Rect,
                        r);     //裁剪后，存放裁剪数据的Rect,目标Rect
                canvas.save();
                canvas.clipRect(r);
                mUnselectedDrawable.draw(canvas);
                canvas.restore();
            }


            //2、画右边彩色

            {
                int w = bounds.width();
                int h = bounds.height();
                if (mOrientation == HORIZONTAL) {
                    w = (int) (w * Math.abs(1 - Math.abs(ratio)));
                }
                if (mOrientation == VERTICAL) {
                    h = (int) (h * Math.abs(1 - Math.abs(ratio)));
                }
                int gravity = ratio < 0 ? Gravity.RIGHT : Gravity.LEFT;

                Gravity.apply(
                        gravity,//从哪个方向开始剪裁图片,如：从左边开始裁剪，从右边开始裁剪
                        w,      //裁剪图片的宽度,目标矩形的宽
                        h,      //裁剪图片的高度，目标矩形的高
                        bounds, //被裁剪的图片的Rect,
                        r);     //裁剪后，存放裁剪数据的Rect,目标Rect
                canvas.save();
                canvas.clipRect(r);
                mSelectedDrawable.draw(canvas);
                canvas.restore();
            }
        }
    }

    @Override
    protected boolean onLevelChange(int level) {
        // 当设置level的时候回调---提醒自己重新绘制
        invalidateSelf();
        LogUtils.i("level=" + level);
        return super.onLevelChange(level);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        LogUtils.i("bounds=" + bounds);
        //定好两张图片的宽高
        mUnselectedDrawable.setBounds(bounds);
        mSelectedDrawable.setBounds(bounds);
    }

    @Override
    public int getIntrinsicWidth() {
        LogUtils.i("getIntrinsicWidth-------------");
        //得到Drawable的实际宽度
        return Math.max(mSelectedDrawable.getIntrinsicWidth(), mUnselectedDrawable.getIntrinsicWidth());
    }

    @Override
    public int getIntrinsicHeight() {
        LogUtils.i("getIntrinsicHeight-------------");
        //得到Drawable的实际高度
        return Math.max(mSelectedDrawable.getIntrinsicHeight(), mUnselectedDrawable.getIntrinsicHeight());
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
