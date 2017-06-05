package com.mei.test.ui.paint.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mei.test.R;

/**
 * Created by mei on 2017/6/3.
 * Description: 放大镜效果
 */
public class ZoomImageView extends View {

    //放大倍数
    private static final int FACTOR = 2;
    //放大镜的半径
    private static final int RADIUS = 100;
    private ShapeDrawable mShapeDrawable;
    private Matrix mMatrix;
    private Bitmap mBitmap;
    private Bitmap mBitmapScale;

    private BitmapShader mBitmapShader;
    private Paint mPaint;

    private int mEventX = RADIUS;//手指触碰屏幕的位置
    private int mEventY = RADIUS;

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy3);
        mBitmapScale = mBitmap;
        mBitmapScale = Bitmap.createScaledBitmap(mBitmapScale, mBitmapScale.getWidth() * FACTOR, mBitmapScale.getHeight() * FACTOR, true);
        mBitmapShader = new BitmapShader(mBitmapScale, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //1.以ShapeDrawable方式实现
        mShapeDrawable = new ShapeDrawable(new OvalShape());
        mShapeDrawable.getPaint().setShader(mBitmapShader);
        // 切出矩形区域，用来画圆（内切圆）
        mShapeDrawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2);

        //2.用画笔自己画
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mBitmapShader);

        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.画原图
        canvas.drawBitmap(mBitmap, 0, 0, null);
        //2.画放大镜的图

        //2-1.以ShapeDrawable方式实现
//        drawZoomBySahpeDrawable(canvas);
        //2-2.以paint的方法实现
        drawZoomByPaint(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mEventX = (int) event.getX();
        mEventY = (int) event.getY();
        invalidate();
        return true;
    }

    private void drawZoomBySahpeDrawable(Canvas canvas) {
        //以手指触摸的位置为圆心
        mMatrix.setTranslate(RADIUS - mEventX * FACTOR, RADIUS - mEventY * FACTOR);
        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
        mShapeDrawable.setBounds(mEventX - RADIUS, mEventY - RADIUS, mEventX + RADIUS, mEventY + RADIUS);
        //2-1.以ShapeDrawable方式实现
        mShapeDrawable.draw(canvas);
    }

    private void drawZoomByPaint(Canvas canvas) {
        //以手指触摸的位置为圆心
        mMatrix.setTranslate(-mEventX, -mEventY);
        mBitmapShader.setLocalMatrix(mMatrix);
        canvas.drawCircle(mEventX, mEventY, RADIUS, mPaint);
    }
}
