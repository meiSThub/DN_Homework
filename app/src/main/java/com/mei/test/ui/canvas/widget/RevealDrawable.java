package com.mei.test.ui.canvas.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

/**
 * Created by mei on 2017/6/29.
 * Description:图片拼接基本实现
 */
public class RevealDrawable extends Drawable {

    //灰色图
    private Drawable mUnselectedDrawable;
    //彩色图
    private Drawable mSelectedDrawable;

    private Rect mTmpRect = new Rect();

    public RevealDrawable(Drawable unselectedDrawable, Drawable selectedDrawable) {
        mUnselectedDrawable = unselectedDrawable;
        mSelectedDrawable = selectedDrawable;
    }

    /**
     * 在draw方法里面将两张图片进行剪裁和拼接
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        Rect r = mTmpRect;

        int w = bounds.width();
        int h = bounds.height();

        //1、画左边
        Gravity.apply(
                Gravity.LEFT,//从哪个方向开始剪裁图片,如：从左边开始裁剪，从右边开始裁剪
                w / 2,  //裁剪图片的宽度,目标矩形的宽
                h,      //裁剪图片的高度，目标矩形的高
                bounds, //被裁剪的图片的Rect,
                r);     //裁剪后，存放裁剪数据的Rect,目标Rect
        canvas.save();
        canvas.clipRect(r);
        mUnselectedDrawable.draw(canvas);
        canvas.restore();

        //2、画右边
        Gravity.apply(
                Gravity.RIGHT,//从哪个方向开始剪裁图片,如：从左边开始裁剪，从右边开始裁剪
                w / 2,  //裁剪图片的宽度,目标矩形的宽
                h,      //裁剪图片的高度，目标矩形的高
                bounds, //被裁剪的图片的Rect,
                r);     //裁剪后，存放裁剪数据的Rect,目标Rect
        canvas.save();
        canvas.clipRect(r);
        mSelectedDrawable.draw(canvas);
        canvas.restore();
    }

    @Override
    protected boolean onLevelChange(int level) {
        //当设置level时，来重绘drawable
        invalidateSelf();
        return super.onLevelChange(level);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        //定好两张图片的宽高
        mUnselectedDrawable.setBounds(bounds);
        mSelectedDrawable.setBounds(bounds);
    }

    @Override
    public int getIntrinsicWidth() {
        return Math.max(mSelectedDrawable.getIntrinsicWidth(), mUnselectedDrawable.getIntrinsicWidth());
    }

    @Override
    public int getIntrinsicHeight() {
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
