package com.mei.test.ui.filter.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mei.test.R;

/**
 * Created by mei on 2017/6/20.
 * Description:滤镜效果view
 */
public class FilterView extends View {

    Paint paint;

    Bitmap bitmap;

    private int progress;

    public FilterView(Context context) {
        super(context);
        init();
    }

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        BlurMaskFilter_Normal(canvas);
//        EmbossMaskFilter(canvas);

//        ColorMatrix(canvas);
//        ColorMatrixSub(canvas);
//        ColorMatrixMix(canvas);
//        ColorMatrixWhiteBlack(canvas);
//        ColorMatrixFaSe(canvas);
//        ColorMatrixOld(canvas);
//        ColorMatrixRoad(canvas);
//        ColorMatrixLight(canvas);
//        LightingColorFilter(canvas);
        PorterDuffColorFilter(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //progress += 1f;
                //progress += 20f;
                progress = 0xff0000;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                progress = 0x000000;
                invalidate();
                break;
        }
        return true;
    }

    public void BlurMaskFilter_Normal(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        RectF rectF = new RectF(100, 100, 300, 300);
        /**
         * BlurMaskFilter主要是改变颜色的 透明度(Alpha) 来实现滤镜效果
         * @param radius 阴影的半径,即模糊的半径范围
         * @param style     NORMAL：整个图像都会被模糊掉
         *                  SOLID：图像边界外产生一层与Paint(图像)颜色一致的阴影效果，不影响图像的本身
         *                  OUTER：图像边界外产生一层阴影，并且将图像变成透明效果
         *                  INNER：在图像内部边沿产生模糊效果
         */
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        canvas.drawRect(rectF, paint);
    }

    public void BlurMaskFilter_Sold(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        RectF rectF = new RectF(100, 100, 300, 300);
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        canvas.drawRect(rectF, paint);
    }

    public void BlurMaskFilter_Outer(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        RectF rectF = new RectF(100, 100, 300, 300);
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));
        canvas.drawRect(rectF, paint);
    }

    public void BlurMaskFilter_Inner(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        RectF rectF = new RectF(100, 100, 300, 300);
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
        canvas.drawRect(rectF, paint);
    }

    public void EmbossMaskFilter(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        RectF rectF = new RectF(100, 100, 600, 600);
        /**
         * Create an emboss maskfilter
         *
         * @param direction  指定光源的位置，长度为xxx的数组标量，array of 3 scalars [x, y, z] specifying the direction of the light source
         * @param ambient    环境光的因子（0~1），越接近0的时候，环境光越暗，0...1 amount of ambient light
         * @param specular   镜面反射系数，，越接近0，镜面反射越强，coefficient for specular highlights (e.g. 8)
         * @param blurRadius 模糊半径,值越大，模糊效果越明显,amount to blur before applying lighting (e.g. 3)
         * @return the emboss maskfilter
         */
        paint.setMaskFilter(new EmbossMaskFilter(new float[]{1, 1, 1}, 0.2f, 60, 80));
        canvas.drawRect(rectF, paint);
    }

    public void ColorMatrix(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 0.5f, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setTextSize(50);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，", 120, 500, paint);
        canvas.drawText("A*0.5f效果对比", 120, 550, paint);
    }

    /**
     * 增加某个颜色值的分量——加法
     *
     * @param canvas
     */
    public void ColorMatrixAdd(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 100,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setTextSize(50);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，", 120, bitmap.getHeight() + 50, paint);
        canvas.drawText("G+100效果对比", 120, bitmap.getHeight() + 100, paint);
    }

    /**
     * 缩放运算——乘法，颜色增强
     *
     * @param canvas
     */
    public void ColorMatrixSub(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1.2f, 0, 0, 0, 0,
                0, 1.2f, 0, 0, 0,
                0, 0, 1.2f, 0, 0,
                0, 0, 0, 1.2f, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setTextSize(50);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，", 120, bitmap.getHeight() + 50, paint);
        canvas.drawText("ARGB分别乘以1.2倍效果对比", 120, bitmap.getHeight() + 100, paint);
    }

    /**
     * 反向效果——相片的底片效果
     *
     * @param canvas
     */
    public void ColorMatrixMix(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);


        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，反向效果", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("ARGB分别乘以-1然后在+255即可反向，效果对比", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * 黑白照片的实现
     * 去色原理：只要把R,G,B三通道的色彩信息设置成一样，那么图像就会变成灰色，
     * 同时为了保证图像亮度不变，同一个通道的值满足R+G+B=1
     *
     * @param canvas
     */
    public void ColorMatrixWhiteBlack(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);


        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，反向效果", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("黑白照片的实现，效果对比", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * 发色效果----（红色和绿色交换）
     *
     * @param canvas
     */
    public void ColorMatrixFaSe(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，反向效果", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("发色效果，效果对比", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * 复古效果
     *
     * @param canvas
     */
    public void ColorMatrixOld(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
                1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
                1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，反向效果", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("复古效果，效果对比", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * 颜色通道过滤
     *
     * @param canvas
     */
    public void ColorMatrixRoad(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，反向效果", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("颜色通道过滤，效果对比", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * 颜色增强，即高亮
     *
     * @param canvas
     */
    public void ColorMatrixLight(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix();
        /**
         *
         *与ColorMatrixSub方法中的矩阵实现效果一样，ColorMatrix封装了很多方法，方便我们使用，避免了自己写矩阵
         * 颜色矩阵的缩放运算就是乘法运算
         */
        colorMatrix.setScale(1.2f, 1.2f, 1.2f, 1);
//        colorMatrix.setSaturation(10f);//增加饱和度,就是加法运算
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，反向效果", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("高亮，效果对比", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * 颜色旋转 --相当于乘法运算
     *
     * @param canvas
     */
    public void ColorMatrixRotate(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix();
        /**
         *@param axis,    0红色轴，1，绿色，2，蓝色
         *@param degrees  旋转的角度
         */
        colorMatrix.setRotate(0, progress);//无论围绕着那个轴旋转，一直增加最终可以回到原点，即最初的值,是封闭循环的
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("通过矩阵，使用颜色滤镜实现，反向效果", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("高亮，效果对比", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * LightingColorFilter 对颜色值RGB的乘法和加法运算
     *
     * @param canvas
     */
    public void LightingColorFilter(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix();

        colorMatrix.setRotate(0, progress);
        /**
         * LightingColorFilter只是修改RGB值，对透明度没有影响
         */
        paint.setColorFilter(new LightingColorFilter(0x00ff00, 0xff0000));
//        paint.setColorFilter(new LightingColorFilter(0xffffff, 0xff0000));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("LightingColorFilter的使用,", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("对颜色值RGB的乘法和加法运算", 0, bitmap.getHeight() + 100, paint);
    }

    /**
     * PorterDuffColorFilter的使用
     *
     * @param canvas
     */
    public void PorterDuffColorFilter(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF, paint);

        ColorMatrix colorMatrix = new ColorMatrix();

        colorMatrix.setRotate(0, progress);
        /**
         * LightingColorFilter只是修改RGB值，对透明度没有影响
         */
//        paint.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.DST_IN));
        paint.setColorFilter(new PorterDuffColorFilter(Color.argb(255, 140, 90, 200), PorterDuff.Mode.MULTIPLY));
        RectF rectF2 = new RectF(600, 0, 600 + bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, null, rectF2, paint);

        paint.setColorFilter(null);
        paint.setTextSize(45);
        paint.setColor(Color.RED);
        canvas.drawText("PorterDuffColorFilter的使用,", 0, bitmap.getHeight() + 50, paint);
        canvas.drawText("对颜色的混合叠加效果实现", 0, bitmap.getHeight() + 100, paint);
    }
}
