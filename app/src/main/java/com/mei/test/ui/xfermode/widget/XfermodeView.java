package com.mei.test.ui.xfermode.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.R;
import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/6/14.
 * Description:
 */
public class XfermodeView extends View {

    private Paint mPaint;
    private Bitmap mBitmapDst;
    private Bitmap mBitmapSrc;

    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.dst);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.src);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.e("canvas--------------");
        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);
//        mPaint.setColor(0x000000);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
       /* canvas.drawColor(Color.BLUE);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(0xff3F51B5);
        mPaint.setTextSize(50);
        canvas.drawText("直接看效果图，先看看效果", 100, 100, mPaint);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawCircle(300, 100, 100, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);*/

        /*int canvasWidth = canvas.getWidth();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, getHeight(), null, Canvas.ALL_SAVE_FLAG);
            int r = canvasWidth / 3;
            //正常绘制黄色的圆形
            mPaint.setColor(0xFFFFCC44);
            canvas.drawCircle(r, r, r, mPaint);
            //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mPaint.setColor(0xFF66AAFF);
            canvas.drawRect(r, r, r * 2.7f, r * 2.7f, mPaint);
            //最后将画笔去除Xfermode
            mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);*/

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
//            mPaint.setColor(0xaa444444);
//            canvas.drawRect(0, 0, getWidth() / 2, getHeight() / 2, mPaint);
            //正常绘制黄色的圆形
            mPaint.setColor(getContext().getResources().getColor(R.color.red_aaFF6340));
            //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
            canvas.drawRect(100, 100, getWidth() / 2 + 100, getHeight() / 2 + 100, mPaint);
            //最后将画笔去除Xfermode
            mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
