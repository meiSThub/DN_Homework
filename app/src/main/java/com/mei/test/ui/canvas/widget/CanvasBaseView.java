package com.mei.test.ui.canvas.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.R;
import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/6/25.
 * Description:Canvas的基本使用
 */
public class CanvasBaseView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;


    public CanvasBaseView(Context context) {
        super(context);
        init();
    }

    public CanvasBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //绘制直线
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lsj);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawLine(canvas);
//        drawLines(canvas);
//        drawPoint(canvas);
//        drawPoints(canvas);
//        drawRect(canvas);
//        drawCircle(canvas);
//        drawRoundRect(canvas);
//        drawOval(canvas);
//        drawArc(canvas);
//        drawPath(canvas);
//        drawRoundPath(canvas);
//        drawOvalPath(canvas);
//        drawRegion(canvas);
//        translate(canvas);
//        translateSave(canvas);
//        rotate(canvas);
//        skew(canvas);
//        saveAndRestore(canvas);
//        clip(canvas);
        saveLayer(canvas);
    }

    public void drawLine(Canvas canvas) {
        canvas.drawLine(0, 0, 600, 600, mPaint);
    }

    //画多条直线(可以实现画虚线)
    public void drawLines(Canvas canvas) {
        float[] pts = {0, 0, 100, 100, 200, 200, 300, 300};
        canvas.drawLines(pts, mPaint);
//        canvas.drawLines(pts, 20, 2, mPaint);// 画虚线--也通过多条线的方式
    }

    //画点
    public void drawPoint(Canvas canvas) {
        canvas.drawPoint(500, 500, mPaint);
    }

    //画多个点
    public void drawPoints(Canvas canvas) {
        float[] pts = {0, 0, 100, 100, 200, 200, 300, 300};
        canvas.drawPoints(pts, mPaint);
    }

    //画矩形
    public void drawRect(Canvas canvas) {
        RectF r = new RectF(100, 100, 400, 500);
        canvas.drawRect(r, mPaint);
    }

    //画圆
    public void drawCircle(Canvas canvas) {
        canvas.drawCircle(200, 200, 100, mPaint);
    }

    //画圆角矩形
    //x-radius ,y-radius圆角的半径
    public void drawRoundRect(Canvas canvas) {
        RectF r = new RectF(100, 100, 400, 500);
//        canvas.drawRoundRect(r, 30, 30, mPaint);

        //30-x轴方向的圆角半径，60-y轴方向上的圆角半径，两者一样就是正常的圆角矩形了
        canvas.drawRoundRect(r, 30, 60, mPaint);
    }

    //画椭圆
    public void drawOval(Canvas canvas) {
        RectF r = new RectF(100, 100, 400, 500);
        canvas.drawOval(r, mPaint);
    }

    //画扇形
    public void drawArc(Canvas canvas) {
        RectF r = new RectF(100, 100, 400, 400);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(r, mPaint);
//		canvas.drawArc(
//				r,
//				startAngle, //起始角度，相对X轴正方向
//				sweepAngle, //画多少角度的弧度
//				useCenter, //boolean, false：只有一个纯弧线；true：闭合的边
//				paint)；
        mPaint.setColor(Color.RED);
//        canvas.drawArc(r, 0, 360f, false, mPaint);//在指定的局域r里面，画出一个内切圆
//        canvas.drawArc(r, 0, 360f, true, mPaint);//在指定的局域r里面，画出一个内切圆

        //如果指定区域是一个正方形，则画出来的是圆形弧（或圆），如果是长方形，则画出的是椭圆弧（或椭圆）
        canvas.drawArc(r, -90, 90f, false, mPaint);//顺时针旋转90度，

    }

    //画路径
    public void drawPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(100, 100);//画笔落笔的位置
        path.lineTo(200, 100);//移动
        path.lineTo(200, 200);
        path.cubicTo(250, 200, 350, 300, 450, 400);
        path.close();

        canvas.drawPath(path, mPaint);
    }

    //画圆角路径
    public void drawRoundPath(Canvas canvas) {
        RectF rectF = new RectF(100, 100, 400, 500);//指定矩形区域
        Path path = new Path();
        //两两一对,分别指定了矩形四个角的圆角半径,如果大小一样，则是圆角，如果不一样，则是椭圆角，从左上角开始
        float radii[] = {10, 10, 10, 10, 10, 10, 50, 60};
        path.addRoundRect(rectF, radii, Path.Direction.CCW);
        canvas.drawPath(path, mPaint);
    }

    //画椭圆路径
    public void drawOvalPath(Canvas canvas) {
        RectF rectF = new RectF(100, 100, 400, 500);
        Path path = new Path();
        path.addOval(rectF, Path.Direction.CCW);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
    }

    /**
     * Region表示Canvas图层上面一块封闭的区域
     *
     * @param canvas
     */
    public void drawRegion(Canvas canvas) {
       /* RectF r = new RectF(100, 100, 600, 800);
        Path path = new Path();
        path.addOval(r, Path.Direction.CCW);*/

        RectF rectF = new RectF(100, 100, 400, 500);//指定矩形区域
        Path path = new Path();
        //两两一对,分别指定了矩形四个角的圆角半径,如果大小一样，则是圆角，如果不一样，则是椭圆角，从左上角开始
        float radii[] = {10, 10, 10, 10, 10, 10, 50, 60};
        path.addRoundRect(rectF, radii, Path.Direction.CCW);

        //创建一块矩形区域
        Region region = new Region(100, 100, 600, 800);
        Region region1 = new Region();
        region1.setPath(path, region);//path的椭圆区域和矩形区域进行交集

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        //综合区域迭代器使用（得到图形里面的所有矩形区域）
        RegionIterator iterator = new RegionIterator(region1);
        Rect rect = new Rect();
        while (iterator.next(rect)) {
            canvas.drawRect(rect, mPaint);
        }
    }

    /**
     * 画布平移
     *
     * @param canvas
     */
    public void translate(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);//原始坐标系

        Rect r = new Rect(0, 0, 400, 500);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(r, mPaint);

        //将画布平移
        canvas.translate(50, 50);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);//平移之后的坐标系

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(r, mPaint);
    }

    /**
     * 画布平移
     *
     * @param canvas
     */
    public void translateSave(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        Rect r = new Rect(0, 0, 400, 500);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(r, mPaint);

        /** 状态栈--save、 restore方法来保存和还原变换操作Matrix以及Clip剪裁
         *
         * 也可以通过restoretoCount直接还原到对应栈的保存状态
         */
        canvas.save();

        //将画布平移
        canvas.translate(50, 50);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(r, mPaint);

        canvas.restore();//把之前保存的状态还原

        mPaint.setColor(Color.YELLOW);
        r = new Rect(0, 0, 200, 200);
        canvas.drawRect(r, mPaint);
    }


    /**
     * 旋转操作
     *
     * @param canvas
     */
    public void rotate(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);//原始坐标系

        Rect r = new Rect(0, 0, 400, 500);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(r, mPaint);

        //将画布平移
        canvas.translate(300, 300);
        canvas.rotate(45);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);//平移之后的坐标系

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(r, mPaint);
    }

    /**
     * 斜拉画布skew
     *
     * @param canvas
     */
    public void skew(Canvas canvas) {

        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(100, 100, 600, 800);
        canvas.drawRect(rectF, mPaint);

        mPaint.setColor(Color.BLUE);
        //sx,sy倾斜度：x轴方向上倾斜60度，tan60=根号3
        canvas.skew(1.73f, 0);
        canvas.drawRect(rectF, mPaint);
    }


    /**
     * Canvas的save和restore方法的运用
     *
     * @param canvas
     */
    public void saveAndRestore(Canvas canvas) {
        canvas.save();//向状态栈中保存Canvas中的状态信息，保存在状态栈中的第一层
        LogUtils.d("Current SaveCount = " + canvas.getSaveCount());

        canvas.translate(400, 400);
        RectF rectF = new RectF(0, 0, 600, 600);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        canvas.save();//第二层
        LogUtils.d("Current SaveCount = " + canvas.getSaveCount());

        canvas.rotate(45);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        canvas.save();//第三层
        LogUtils.d("Current SaveCount = " + canvas.getSaveCount());

        canvas.rotate(45);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        canvas.save();//第四层
        LogUtils.d("Current SaveCount = " + canvas.getSaveCount());

        canvas.restoreToCount(1);//恢复到第一层，状态出栈
        LogUtils.d("Current SaveCount = " + canvas.getSaveCount());

        canvas.translate(0, 200);

        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
    }

    /**
     * Canvas剪裁
     *
     * @param canvas
     */
    public void clip(Canvas canvas) {
        canvas.drawColor(Color.RED);
        canvas.save();//第一层
        LogUtils.d("Current SaveCount = " + canvas.getSaveCount());

        canvas.clipRect(100, 100, 800, 800);
        canvas.drawColor(Color.GREEN);
        canvas.save();//第二层

        canvas.clipRect(200, 200, 700, 700);
        canvas.drawColor(Color.BLUE);
        canvas.save();//第三层

        canvas.clipRect(300, 300, 600, 600);
        canvas.drawColor(Color.BLACK);
        canvas.save();//第四层

        canvas.clipRect(400, 400, 500, 500);
        canvas.drawColor(Color.WHITE);
        canvas.save();//第五层

        //1.恢复到第五层,此时Canvas的Matrix和clip信息就对应着第五层的画布信息，所以白色会被替换成黄色
        /*canvas.restore();//恢复到第五层
        canvas.drawColor(Color.YELLOW);*/

        //2、恢复到底四层,黑色会被替换成黄色
        /*canvas.restore();//恢复到第五层
        canvas.restore();//恢复到第四层,那么此时canvas的Matrix、clip信息都是第四层的,相当于操作第四层的画布一样
        canvas.drawColor(Color.YELLOW);*/

        //3、恢复到底三层、蓝色被替换成黄色
        /*canvas.restore();//恢复到第五层
        canvas.restore();//恢复到第四层
        canvas.restore();//恢复到第三层
        canvas.drawColor(Color.YELLOW);*/

        //4、恢复到第二层、绿色被替换成黄色
       /* canvas.restore();//恢复到第五层
        canvas.restore();//恢复到第四层
        canvas.restore();//恢复到第三层
        canvas.restore();//恢复到第二层
        canvas.drawColor(Color.YELLOW);*/

        //5、恢复到第一层、红色被替换成黄色
        /*canvas.restore();//恢复到第五层
        canvas.restore();//恢复到第四层
        canvas.restore();//恢复到第三层
        canvas.restore();//恢复到第二层
        canvas.restore();//恢复到第一层
        canvas.drawColor(Color.YELLOW);*/
//// TODO: 2017/6/27 restore()方法只能比save（）方法少，不能比save()方法调用的次数多，否则报错

        //5、恢复到指定层、这里恢复到第二层，所以绿色被替换成黄色
        canvas.restoreToCount(2);
        canvas.drawColor(Color.YELLOW);
    }

    /**
     * Canvas的saveLayer方法的使用
     * save()是把Canvas的状态信息（如：Matrix、clip等信息）保存到状态栈，而saveLayer方法是保存当前图层，并新建一个透明的空白图层,
     * 这是两者的本质区别
     *
     * @param canvas
     */
    public void saveLayer(Canvas canvas) {

        RectF rectF = new RectF(0, 0, 400, 500);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        paint.setColor(Color.GREEN);
        canvas.drawRect(rectF, paint);

        canvas.translate(50, 50);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
//        canvas.save();
        canvas.drawColor(Color.DKGRAY);

        paint.setColor(Color.YELLOW);
        canvas.drawRect(rectF, paint);

        /*paint.setColor(Color.BLUE);
        canvas.drawRect(-20, -20, 400, 400, paint);*/

        canvas.restoreToCount(layerId);
//        canvas.restore();

        RectF rectF1 = new RectF(20, 20, 300, 400);
        paint.setColor(Color.RED);
        canvas.drawRect(rectF1, paint);
    }
}































