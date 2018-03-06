package ubt.com.test.image.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ubt on 2017/12/22.
 * 通过SurfaceView实现自定义View的基本步骤
 * 1、创建一个自定义View，继承SurfaceView，实现SurfaceHolder.Callback, Runnable这两个接口
 * 2、初始化SurfaceView，主要是SurfaceHolder（通过SurfaceView的getHolder方法可以得到），用于绘图的Canvas，子线程标志位
 * 3、使用SurfaceView
 *
 * @description:
 */

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;//用于绘图的Canvas
    private boolean mIsDrawing;//子线程标志位
    private float y;
    private int x;
    private Path mPath;
    private Paint mPaint;

    public CustomSurfaceView(Context context) {
        super(context);
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }


    /**
     * SurfaceView的创建
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("plum", "surfaceCreated");
        mIsDrawing = true;
        new Thread(this).start();
    }

    /**
     * SurfaceView的刷新
     *
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("plum", "surfaceChanged");
    }

    /**
     * SurfaceView的销毁
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("plum", "surfaceDestroyed");
        mIsDrawing = false;
    }

    @Override
    public void run() {
        Log.i("plum", "run");
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
//            drawSin();
            x += 1;
            y = (float) (100 * Math.sin((x * 2 * Math.PI / 180) + 400));
            mPath.lineTo(x, y);
            drawGesture();
        }
        //优化，节约资源
        long end = System.currentTimeMillis();
        if (end - start < 100) {
            try {
                Thread.sleep(100 - (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 画正弦曲线
     */
    private void drawSin() {

        try {

            mCanvas = mHolder.lockCanvas();
            mCanvas.translate(100, 200);
            //draw something
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //对画布绘制的内容进行提交，显示到屏幕上
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 绘图板实现
     */
    private void drawGesture() {

        try {
            Log.i("plum", "drawGesture");
            mCanvas = mHolder.lockCanvas();
            mCanvas.translate(100, 200);
            //draw something
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mGesturesPath, mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //对画布绘制的内容进行提交，显示到屏幕上
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    private Path mGesturesPath = new Path();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mGesturesPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mGesturesPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
