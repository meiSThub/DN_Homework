package com.mei.test.ui.paint.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.R;
import com.mei.test.utils.LogUtils;

/**
 * Created by VST on 2017/6/6.
 * Description:
 */

public class RadarView extends View {

    //设置默认宽高，雷达一般都是圆形，所以我们下面取宽高会去Math.min(宽,高)
    private final int DEFAULT_WIDTH = 200;

    private final int DEFAULT_HEIGHT = 200;

    private Matrix mMatrix;
    private SweepGradient mGradient;
    private Paint mPaint;
    private float mDegree;
    //雷达的半径
    private int mRadarRadius;
    //雷达画笔
    private Paint mRadarPaint;
    //雷达底色画笔
    private Paint mRadarBg;
    //雷达圆圈的个数，默认4个
    private int mCircleNum = 4;
    //雷达线条的颜色，默认为白色
    private int mCircleColor = Color.WHITE;
    //雷达圆圈背景色
    private int mRadarBgColor = Color.BLACK;

    //雷达扫描时候的起始和终止颜色
    private int mStartColor = 0x0000ff00;

    private int mEndColor = 0xaa00ff00;

    private int mCenterX = 0;//圆心坐标
    private int mCenterY = 0;

    private int mLineWidth = 4;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
            mCircleColor = array.getColor(R.styleable.RadarView_lineColor, mCircleColor);
            mCircleNum = array.getInt(R.styleable.RadarView_circleNum, mCircleNum);
            mRadarBgColor = array.getColor(R.styleable.RadarView_backgroundColor, mRadarBgColor);
            mStartColor = array.getColor(R.styleable.RadarView_startColor, mStartColor);
            mEndColor = array.getColor(R.styleable.RadarView_endColor, mEndColor);
        }
        init();
    }

    private void init() {
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mGradient);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadarRadius = Math.min(w / 2, h / 2);
        mCenterX = mRadarRadius;
        mCenterY = mRadarRadius;
        mGradient = new SweepGradient(mCenterX, mCenterY, mStartColor, mEndColor);
        LogUtils.e("onSizeChanged-----w=" + w + ";h=" + h);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(1, DEFAULT_WIDTH, widthMeasureSpec);
        int height = measureSize(0, DEFAULT_HEIGHT, heightMeasureSpec);
        LogUtils.e("onMeasure--------- width=" + width + ";height=" + height);
        //取最大的 宽|高
        int measureSize = Math.max(width, height);
        setMeasuredDimension(measureSize, measureSize);
    }

    /**
     * 测绘measure
     *
     * @param specType    1为宽， 其他为高
     * @param contentSize 默认值
     */
    private int measureSize(int specType, int contentSize, int measureSpec) {
        int result;
        //获取测量的模式和Size
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = Math.max(contentSize, specSize);
        } else {
            result = contentSize;

            if (specType == 1) {
                // 根据传人方式计算宽
                result += (getPaddingLeft() + getPaddingRight());
            } else {
                // 根据传人方式计算高
                result += (getPaddingTop() + getPaddingBottom());
            }
        }

        return result;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mRadarBgColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCenterX, mCenterY, mRadarRadius, mPaint);

        mPaint.setColor(mCircleColor);
        mPaint.setStrokeWidth(mLineWidth);
        canvas.drawLine(mLineWidth, mRadarRadius, mRadarRadius * 2 - mLineWidth, mRadarRadius, mPaint);//横线
        canvas.drawLine(mRadarRadius, mLineWidth, mRadarRadius, mRadarRadius * 2 - mLineWidth, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 1; i <= mCircleNum; i++) {
            canvas.drawCircle(mCenterX, mCenterY, mRadarRadius * i / mCircleNum, mPaint);
        }

        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mGradient);
        mMatrix.setRotate(mDegree, mCenterX, mCenterY);
        mGradient.setLocalMatrix(mMatrix);
        canvas.drawCircle(mCenterX, mCenterY, mRadarRadius - mLineWidth, mPaint);
        //canvas.restore();
    }

    public void startScan() {
        mHandler.removeMessages(1);
        mHandler.sendEmptyMessage(1);
    }

    public void stopScan() {
        mHandler.removeMessages(1);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mDegree = ++mDegree % 360;
            postInvalidate();
            sendEmptyMessageDelayed(1, 30);
        }
    };
}
