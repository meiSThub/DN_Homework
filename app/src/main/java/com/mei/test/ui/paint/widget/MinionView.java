package com.mei.test.ui.paint.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.mei.test.utils.LogUtils;


/**
 * Created by mei on 2017/5/21.
 * Description:
 */
public class MinionView extends View {
    private static final int DEFAULT_SIZE = 200; //View默认大小
    private static final float BODY_WIDTH_HEIGHT_SCALE = 0.6f; //        身体的比例设定为 w:h = 3:5
    private static final float BODY_SCALE = 0.6f;//身体主干占整个view的比重

    private int mWidthUnspecified;
    private int mHeightUnspecified;
    private float mBodyWidth;
    private float mBodyHeight;
    private float mStrokeWidth;
    private float mOffset;
    private RectF mBodyRect;
    private float mRadius;
    private float mFootHeight;
    private float mHandsHeight;
    private Paint mPaint;
    private int mColorClothes = Color.rgb(32, 116, 160);//衣服的颜色
    private int mColorBody = Color.rgb(249, 217, 70);//衣服的颜色
    private int mColorStroke = Color.BLACK;

    public MinionView(Context context) {
        this(context, null);
    }

    public MinionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MinionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    /**
     * @param origin
     * @param isWidth 是否在测量宽
     * @return
     */
    private int measure(int origin, boolean isWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result = specSize;
                if (isWidth) {
                    mWidthUnspecified = result;
                } else {
                    mHeightUnspecified = result;
                }
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                if (isWidth) {//宽或高未定的情况下，可以由另一端推算出来---如果两边都没有制定就用默认值
                    result = (int) (mHeightUnspecified * BODY_WIDTH_HEIGHT_SCALE);
                }
                if (result == 0) {
                    result = DEFAULT_SIZE;
                }
                break;
        }
        LogUtils.e("result=" + result);
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.i("onDraw-----------------------");
        initParams();
        initPaint();
        drawBody(canvas);
        drawBodyStroke(canvas);
        drawClothes(canvas);
        drawEyesMouth(canvas);
        drawFeet(canvas);
        drawFeetShadow(canvas);
        drawHands(canvas);
    }

    private void initParams() {
        mBodyWidth = Math.min(getWidth(), getHeight() * BODY_WIDTH_HEIGHT_SCALE) * BODY_SCALE;
        mBodyHeight = Math.min(getWidth(), getHeight() * BODY_WIDTH_HEIGHT_SCALE) / BODY_WIDTH_HEIGHT_SCALE * BODY_SCALE;

        mStrokeWidth = Math.max(mBodyWidth / 50, mStrokeWidth);
        mOffset = mStrokeWidth / 2;

        mBodyRect = new RectF();
        mBodyRect.left = (getWidth() - mBodyWidth) / 2;
        mBodyRect.top = (getHeight() - mBodyHeight) / 2;
        mBodyRect.right = mBodyRect.left + mBodyWidth;
        mBodyRect.bottom = mBodyRect.top + mBodyHeight;

        mRadius = mBodyWidth / 2;
        mFootHeight = mRadius * 0.4333f;
        mHandsHeight = (getHeight() + mBodyHeight) / 2 + mOffset - mRadius * 1.65f;
    }

    /**
     * 画身体
     *
     * @param canvas
     */
    private void drawBody(Canvas canvas) {
        initPaint();
        mPaint.setColor(mColorBody);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(mBodyRect, mRadius, mRadius, mPaint);
    }

    /**
     * 画边框
     *
     * @param canvas
     */
    private void drawBodyStroke(Canvas canvas) {
        initPaint();
        mPaint.setColor(mColorStroke);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(mBodyRect, mRadius, mRadius, mPaint);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawClothes(Canvas canvas) {
        initPaint();
        RectF rect = new RectF();
        rect.left = mBodyRect.left + mOffset;
        rect.top = (getHeight() + mBodyHeight) / 2 - mRadius * 2 + mOffset;
        rect.right = mBodyRect.right - mOffset;
        rect.bottom = mBodyRect.bottom - mOffset;
        mPaint.setColor(mColorClothes);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawArc(rect, 0, 180, true, mPaint);

        int h = (int) (mRadius * 0.5);
        int w = (int) (mRadius * 0.3);
        rect.left += w;
        rect.top = rect.top + mRadius - h;
        rect.right -= w;
        rect.bottom = rect.top + h;
        canvas.drawRect(rect, mPaint);

        //画横线
        initPaint();
        mPaint.setColor(mColorStroke);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mStrokeWidth);
        float[] pts = new float[20];//5条线
        pts[0] = rect.left - w;
        pts[1] = rect.top + h;
        pts[2] = pts[0] + w;
        pts[3] = pts[1];

        pts[4] = pts[2];
        pts[5] = pts[3] + mOffset;
        pts[6] = pts[4];
        pts[7] = pts[3] - h;

        pts[8] = pts[6] - mOffset;
        pts[9] = pts[7];
        pts[10] = pts[8] + (mRadius - w) * 2;
        pts[11] = pts[9];

        pts[12] = pts[10];
        pts[13] = pts[11] - mOffset;
        pts[14] = pts[12];
        pts[15] = pts[13] + h;

        pts[16] = pts[14] - mOffset;
        pts[17] = pts[15];
        pts[18] = pts[16] + w;
        pts[19] = pts[17];
        canvas.drawLines(pts, mPaint);

        //画左吊带
        initPaint();
        mPaint.setColor(mColorClothes);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(rect.left - w - mOffset, mHandsHeight);
        path.lineTo(rect.left + h / 4, rect.top + h / 2);
        final float smallW = w / 2 * (float) Math.sin(Math.PI / 4);
        path.lineTo(rect.left + h / 4 + smallW, rect.top + h / 2 - smallW);
        final float smallW2 = w / (float) Math.sin(Math.PI / 4) / 2;
        path.lineTo(rect.left - w - mOffset, mHandsHeight - smallW2);
        canvas.drawPath(path, mPaint);

        initPaint();
        mPaint.setColor(mColorStroke);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(rect.left + h / 5, rect.top + h / 4, mStrokeWidth * 0.7f, mPaint);

        //画右吊带

        initPaint();
        mPaint.setColor(mColorClothes);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(rect.left - w + 2 * mRadius - mOffset, mHandsHeight);
        path.lineTo(rect.right - h / 4, rect.top + h / 2);
        path.lineTo(rect.right - h / 4 - smallW, rect.top + h / 2 - smallW);
        path.lineTo(rect.left - w + 2 * mRadius - mOffset, mHandsHeight - smallW2);

        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setColor(mColorStroke);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(rect.right - h / 5, rect.top + h / 4, mStrokeWidth * 0.7f, mPaint);

        //中间口袋
        initPaint();
        mPaint.setColor(mColorStroke);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        path.reset();
        float radiusBigPokect = w / 2.0f;
        path.moveTo(rect.left + 1.5f * w, rect.bottom - h / 4);
        path.lineTo(rect.right - 1.5f * w, rect.bottom - h / 4);
        path.lineTo(rect.right - 1.5f * w, rect.bottom + h / 4);
        path.addArc(rect.right - 1.5f * w - radiusBigPokect * 2, rect.bottom + h / 4 - radiusBigPokect,
                rect.right - 1.5f * w, rect.bottom + h / 4 + radiusBigPokect, 0, 90);
        path.lineTo(rect.left + 1.5f * w + radiusBigPokect, rect.bottom + h / 4 + radiusBigPokect);

        path.addArc(rect.left + 1.5f * w, rect.bottom + h / 4 - radiusBigPokect,
                rect.left + 1.5f * w + 2 * radiusBigPokect, rect.bottom + h / 4 + radiusBigPokect, 90, 90);
        path.lineTo(rect.left + 1.5f * w, rect.bottom - h / 4 - mOffset);
        canvas.drawPath(path, mPaint);

        //下边一竖，分开裤子
        canvas.drawLine(mBodyRect.left + mBodyWidth / 2, mBodyRect.bottom - h * 0.8f, mBodyRect.left + mBodyWidth / 2, mBodyRect.bottom, mPaint);
        //左边小口袋
        float radiusSmallPoket = w * 1.2f;
        canvas.drawArc(mBodyRect.left - radiusSmallPoket, mBodyRect.bottom - mRadius - radiusSmallPoket,
                mBodyRect.left + radiusSmallPoket, mBodyRect.bottom - mRadius + radiusSmallPoket, 80, -60, false, mPaint);
        //右边小口袋
        canvas.drawArc(mBodyRect.right - radiusSmallPoket, mBodyRect.bottom - mRadius - radiusSmallPoket,
                mBodyRect.right + radiusSmallPoket, mBodyRect.bottom - mRadius + radiusSmallPoket, 100, 60, false, mPaint);
    }

    /**
     * 画眼睛和嘴
     *
     * @param canvas
     */
    private void drawEyesMouth(Canvas canvas) {

        float eyesOffset = mRadius * 0.1f;//眼睛中心处于上半圆直径 往上的高度偏移
        mPaint.setStrokeWidth(mStrokeWidth * 5);
//        计算眼镜带弧行的半径 分两段，以便眼睛中间有隔开的效果
        float radiusGlassesRibbon = (float) (mRadius / Math.sin(Math.PI / 20));
        RectF rect = new RectF();
        rect.left = mBodyRect.left + mRadius - radiusGlassesRibbon;
        rect.top = mBodyRect.top + mRadius - (float) (mRadius / Math.tan(Math.PI / 20)) - radiusGlassesRibbon - eyesOffset;
        rect.right = rect.left + radiusGlassesRibbon * 2;
        rect.bottom = rect.top + radiusGlassesRibbon * 2;
        canvas.drawArc(rect, 81, 3, false, mPaint);
        canvas.drawArc(rect, 99, -3, false, mPaint);

//眼睛半径
        float radiusEyes = mRadius / 3;
        initPaint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 - radiusEyes - mOffset, mBodyRect.top + mRadius - eyesOffset, radiusEyes, mPaint);
        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 + radiusEyes + mOffset, mBodyRect.top + mRadius - eyesOffset, radiusEyes, mPaint);

        mPaint.setColor(mColorStroke);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 - radiusEyes - mOffset, mBodyRect.top + mRadius - eyesOffset, radiusEyes, mPaint);
        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 + radiusEyes + mOffset, mBodyRect.top + mRadius - eyesOffset, radiusEyes, mPaint);

        final float radiusEyeballBlack = radiusEyes / 3;
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 - radiusEyes - mOffset, mBodyRect.top + mRadius - eyesOffset, radiusEyeballBlack, mPaint);
        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 + radiusEyes + mOffset, mBodyRect.top + mRadius - eyesOffset, radiusEyeballBlack, mPaint);

        mPaint.setColor(Color.WHITE);
        final float radiusEyeballWhite = radiusEyeballBlack / 2;
        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 - radiusEyes + radiusEyeballWhite - mOffset * 2,
                mBodyRect.top + mRadius - radiusEyeballWhite + mOffset - eyesOffset,
                radiusEyeballWhite, mPaint);
        canvas.drawCircle(mBodyRect.left + mBodyWidth / 2 + radiusEyes + radiusEyeballWhite,
                mBodyRect.top + mRadius - radiusEyeballWhite + mOffset - eyesOffset,
                radiusEyeballWhite, mPaint);

//        画嘴巴，因为位置和眼睛有相对关系，所以写在一块
        mPaint.setColor(mColorStroke);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        float radiusMonth = mRadius;
        rect.left = mBodyRect.left;
        rect.top = mBodyRect.top - radiusMonth / 2.5f;
        rect.right = rect.left + radiusMonth * 2;
        rect.bottom = rect.top + radiusMonth * 2;
        canvas.drawArc(rect, 95, -20, false, mPaint);

    }

    /**
     * 画脚
     *
     * @param canvas
     */
    private void drawFeet(Canvas canvas) {
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(mColorStroke);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        float radiusFoot = mRadius / 3 * 0.4f;
        float leftFootStartX = mBodyRect.left + mRadius - mOffset * 2;
        float leftFootStartY = mBodyRect.bottom - mOffset;
        float footWidthA = mRadius * 0.5f;//脚宽度大-到半圆结束
        float footWidthB = footWidthA / 3;//脚宽度-比较细的部分

        //      左脚
        Path path = new Path();
        path.moveTo(leftFootStartX, leftFootStartY);
        path.lineTo(leftFootStartX, leftFootStartY + mFootHeight);
        path.lineTo(leftFootStartX - footWidthA + radiusFoot, leftFootStartY + mFootHeight);

        RectF rectF = new RectF();
        rectF.left = leftFootStartX - footWidthA;
        rectF.top = leftFootStartY + mFootHeight - radiusFoot * 2;
        rectF.right = rectF.left + radiusFoot * 2;
        rectF.bottom = rectF.top + radiusFoot * 2;
        path.addArc(rectF, 90, 180);
        path.lineTo(rectF.left + radiusFoot + footWidthB, rectF.top);
        path.lineTo(rectF.left + radiusFoot + footWidthB, leftFootStartY);
        path.lineTo(leftFootStartX, leftFootStartY);
        canvas.drawPath(path, mPaint);

//      右脚
        float rightFootStartX = mBodyRect.left + mRadius + mOffset * 2;
        float rightFootStartY = leftFootStartY;
        path.reset();
        path.moveTo(rightFootStartX, rightFootStartY);
        path.lineTo(rightFootStartX, rightFootStartY + mFootHeight);
        path.lineTo(rightFootStartX + footWidthA - radiusFoot, rightFootStartY + mFootHeight);

        rectF.left = rightFootStartX + footWidthA - radiusFoot * 2;
        rectF.top = rightFootStartY + mFootHeight - radiusFoot * 2;
        rectF.right = rectF.left + radiusFoot * 2;
        rectF.bottom = rectF.top + radiusFoot * 2;
        path.addArc(rectF, 90, -180);
        path.lineTo(rectF.right - radiusFoot - footWidthB, rectF.top);
        path.lineTo(rectF.right - radiusFoot - footWidthB, rightFootStartY);
        path.lineTo(rightFootStartX, rightFootStartY);
        canvas.drawPath(path, mPaint);


    }

    /**
     * 画脚下的阴影
     *
     * @param canvas
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawFeetShadow(Canvas canvas) {

        mPaint.setColor(getResources().getColor(android.R.color.darker_gray));
        canvas.drawOval(mBodyRect.left + mBodyWidth * 0.15f, mBodyRect.bottom - mOffset + mFootHeight,
                mBodyRect.right - mBodyWidth * 0.15f, mBodyRect.bottom - mOffset + mFootHeight + mStrokeWidth * 1.3f, mPaint);
    }


    /**
     * 画手
     *
     * @param canvas
     */
    private void drawHands(Canvas canvas) {
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(mColorBody);

//        左手
        Path path = new Path();
        float hypotenuse = mBodyRect.bottom - mRadius - mHandsHeight;
        float radiusHand = hypotenuse / 6;
        mPaint.setPathEffect(new CornerPathEffect(radiusHand));

        path.moveTo(mBodyRect.left, mHandsHeight);
        path.lineTo(mBodyRect.left - hypotenuse / 2, mHandsHeight + hypotenuse / 2);
        path.lineTo(mBodyRect.left + mOffset, mBodyRect.bottom - mRadius + mOffset);
        path.lineTo(mBodyRect.left, mHandsHeight);//增加兼容性,path没闭合在一起机子上会使手的下面的点没办法与裤子重合
        canvas.drawPath(path, mPaint);

        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColorStroke);
        canvas.drawPath(path, mPaint);


//        右手
        path.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColorBody);

        path.moveTo(mBodyRect.right, mHandsHeight);
        path.lineTo(mBodyRect.right + hypotenuse / 2, mHandsHeight + hypotenuse / 2);
        path.lineTo(mBodyRect.right - mOffset, mBodyRect.bottom - mRadius + mOffset);
        path.lineTo(mBodyRect.right, mHandsHeight);
        canvas.drawPath(path, mPaint);

        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColorStroke);
        canvas.drawPath(path, mPaint);

//        一个慢动作  - -||| 拐点内侧
        path.reset();
        mPaint.setStyle(Paint.Style.FILL);
        path.moveTo(mBodyRect.left, mHandsHeight + hypotenuse / 2 - mStrokeWidth);
        path.lineTo(mBodyRect.left - mStrokeWidth * 2, mHandsHeight + hypotenuse / 2 + mStrokeWidth * 2);
        path.lineTo(mBodyRect.left, mHandsHeight + hypotenuse / 2 + mStrokeWidth);
        path.lineTo(mBodyRect.left, mHandsHeight + hypotenuse / 2 - mStrokeWidth);
        canvas.drawPath(path, mPaint);

        path.reset();
        path.moveTo(mBodyRect.right, mHandsHeight + hypotenuse / 2 - mStrokeWidth);
        path.lineTo(mBodyRect.right + mStrokeWidth * 2, mHandsHeight + hypotenuse / 2 + mStrokeWidth * 2);
        path.lineTo(mBodyRect.right, mHandsHeight + hypotenuse / 2 + mStrokeWidth);
        path.lineTo(mBodyRect.right, mHandsHeight + hypotenuse / 2 - mStrokeWidth);
        canvas.drawPath(path, mPaint);

    }

    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
        } else {
            mPaint.reset();
        }
        mPaint.setAntiAlias(true);//边缘无锯齿
    }
}
