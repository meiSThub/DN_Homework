package ubt.com.test.image.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ubt on 2017/12/22.
 * SurfaceView使用的模板代码
 * <p>
 * 通过SurfaceView实现自定义View的基本步骤
 * 1、创建一个自定义View，继承SurfaceView，实现SurfaceHolder.Callback, Runnable这两个接口
 * 2、初始化SurfaceView，主要是SurfaceHolder（通过SurfaceView的getHolder方法可以得到），用于绘图的Canvas，子线程标志位
 * 3、使用SurfaceView
 *
 * @description:
 */

public class SurfaceViewModel extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;//用于绘图的Canvas
    private boolean mIsDrawing;//子线程标志位

    public SurfaceViewModel(Context context) {
        super(context);
        init();
    }

    public SurfaceViewModel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewModel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mCanvas = new Canvas();
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);

    }


    /**
     * SurfaceView的创建
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
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

    }

    /**
     * SurfaceView的销毁
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) draw();
    }

    private void draw() {

        try {
            mCanvas = mHolder.lockCanvas();
            //draw something
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //对画布绘制的内容进行提交，显示到屏幕上
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

}
