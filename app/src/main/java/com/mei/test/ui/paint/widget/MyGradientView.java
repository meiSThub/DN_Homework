package com.mei.test.ui.paint.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.R;


/**
 * Created by mei on 2017/6/1.
 * Description: Shader练习
 */
public class MyGradientView extends View {

    private Bitmap mBitMap;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public MyGradientView(Context context) {
        super(context);
        init();
    }

    public MyGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitMap = ((BitmapDrawable) getResources().getDrawable(R.drawable.xyjy2)).getBitmap();
        mPaint = new Paint();
        mWidth = mBitMap.getWidth();
        mHeight = mBitMap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);//背景
//        drawCircleIcon(canvas);
//        drawOvalHead(canvas);
//        drawRectHead(canvas);
//        drawCircleHeadByShapeShader(canvas);
//        drawGradient(canvas);
//        drawRadialGradient(canvas);
        drawSweepGradient(canvas);
    }

    /**
     * 画圆形头像
     * 用图片来填充圆形图形,即可实现圆形头像效果
     *
     * @param canvas
     */
    private void drawCircleIcon(Canvas canvas) {
        /**
         * 当图片比我们指定的图形(如：Rect)区域小的时候，会根据我们指定的拉伸模式，来拉伸我们的图片,有如下的拉伸模式可供选择
         * Shader.TileMode 拉伸的类型，
         * Shader.TileMode.CLAMP：以图片边缘的像素点来拉伸
         * Shader.TileMode.REPEAT：图片重复显示
         * Shader.TileMode.MIRROR：镜像拉伸，像倒影
         *
         */
        BitmapShader bitmapShader = new BitmapShader(mBitMap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setShader(bitmapShader);
        mPaint.setAntiAlias(true);
        float scale = ((float) Math.max(mWidth, mHeight)) / Math.min(mWidth, mHeight);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        canvas.drawCircle(mHeight / 2, mHeight / 2, mHeight / 2, mPaint);
    }

    /**
     * 椭圆形头像
     *
     * @param canvas
     */
    private void drawOvalHead(Canvas canvas) {
        BitmapShader bitmapShader = new BitmapShader(mBitMap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaint.setShader(bitmapShader);
        mPaint.setAntiAlias(true);
        canvas.drawOval(new RectF(0, 0, mWidth, mHeight), mPaint);
    }

    /**
     * 矩形头像
     *
     * @param canvas
     */
    private void drawRectHead(Canvas canvas) {

        BitmapShader bitmapShader = new BitmapShader(mBitMap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
        canvas.drawRect(new Rect(0, 0, 600, 800), mPaint);
    }

    /**
     * 通过ShapeDrawable来实现圆形头像，其实就是通过ShapeDrawable来指定一个圆形的区域，相当于是封装了一下(里面封装了画笔，图形区域和绘制方法)
     *
     * @param canvas
     */
    private void drawCircleHeadByShapeShader(Canvas canvas) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        BitmapShader bitmapShader = new BitmapShader(mBitMap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(0, 0, mWidth, mWidth);
        shapeDrawable.draw(canvas);
    }


    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
//    private int[] mColors = {Color.RED, Color.GREEN};

    private void drawGradient(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800, mColors, null, Shader.TileMode.CLAMP);
//        LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800,Color.RED,Color.YELLOW ,Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRect(0, 0, 800, 800, mPaint);
    }

    /**
     * 圆形渐变
     * @param canvas
     */
    private void drawRadialGradient(Canvas canvas) {
        RadialGradient radialGradient = new RadialGradient(400, 400, 400, mColors, null, Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(400, 400, 400, mPaint);
    }

    /**
     * 梯度渐变
     * @param canvas
     */
    private void drawSweepGradient(Canvas canvas) {
        /*SweepGradient sweepGradient = new SweepGradient(400, 400, mColors, null);
        mPaint.setShader(sweepGradient);
        canvas.drawCircle(400, 400, 300, mPaint);*/

        SweepGradient sweepGradient = new SweepGradient(400, 400, mColors, null);
        mPaint.setShader(sweepGradient);
//        canvas.drawCircle(400, 400, 300, mPaint);
        canvas.drawRect(100,100,700,700,mPaint);
    }

    private void drawComposeShader(Canvas canvas){
        /***************用ComposeShader即可实现心形图渐变效果*********************************/
        //创建BitmapShader，用以绘制心
        BitmapShader bitmapShader = new BitmapShader(mBitMap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //创建LinearGradient，用以产生从左上角到右下角的颜色渐变效果
        LinearGradient linearGradient = new LinearGradient(0, 0, mWidth, mHeight, Color.GREEN, Color.BLUE, Shader.TileMode.CLAMP);
        //bitmapShader对应目标像素，linearGradient对应源像素，像素颜色混合采用MULTIPLY模式
        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
        //将组合的composeShader作为画笔paint绘图所使用的shader
        mPaint.setShader(composeShader);
        //用composeShader绘制矩形区域
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }
}




































