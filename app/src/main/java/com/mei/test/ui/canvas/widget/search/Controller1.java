package com.mei.test.ui.canvas.widget.search;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/7/4.
 * Description:
 */
public class Controller1 extends BaseController {
    private static final String TAG = "Controller1";

    private String mColor = "#4caf50";

    private int mCircleX;//圆心

    private int mCircleY;

    private int mRadius;//圆半径

    /**
     * 圆Rect区域
     */
    private RectF mRectF;

    private int mDeltaD = 15;//每次向右移动的距离


    public Controller1() {
        mRectF = new RectF();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.parseColor(mColor));
        switch (mState) {
            case STATE_ANIM_NONE:
                drawNormalView(paint, canvas);
                break;
            case STATE_ANIM_START:
                drawStartAnimView(paint, canvas);
                break;
            case STATE_ANIM_STOP:
                revertAnimation(paint, canvas);
                break;
        }
    }

    private void drawNormalView(Paint paint, Canvas canvas) {
        mRadius = getWidth() / 20;
        LogUtils.i("plum", "mRadius=" + mRadius);
        mCircleX = getWidth() / 2;
        mCircleY = getHeight() / 2;

        mRectF.left = mCircleX - mRadius;
        mRectF.right = mCircleX + mRadius;
        mRectF.top = mCircleY - mRadius;
        mRectF.bottom = mCircleY + mRadius;

//		canvas.drawArc(
//		r,
//		startAngle, //起始角度，相对X轴正方向
//		sweepAngle, //画多少角度的弧度
//		useCenter, //boolean, false：只有一个纯弧线；true：闭合的边
//		paint)；
//		canvas.drawArc(r, 0, 90, true, paint);//顺时针旋转90度

        canvas.save();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        canvas.rotate(45, mCircleX, mCircleY);//x轴正方向为角度的起始点，也就是0度，旋转角度大于0位顺时针方向
        canvas.drawLine(mCircleX + mRadius, mCircleY, mCircleX + mRadius * 2, mCircleY, paint);
        canvas.drawArc(mRectF,
                45,
                360,
                false,
                paint);
        canvas.restore();
    }

    /**
     * 展开动画
     *
     * @param paint
     * @param canvas
     */
    private void drawStartAnimView(Paint paint, Canvas canvas) {
        canvas.save();
        LogUtils.i("plum", "mpro=" + mpro);
        if (mpro <= 0.5f) {
            //绘制圆和把手
            canvas.drawArc(mRectF,
                    45,
                    360 * (mpro * 2 - 1),//逆时针方向画圆弧
                    false,
                    paint);

            canvas.drawLine(
                    mRectF.right - mDeltaD,
                    mRectF.bottom - mDeltaD,
                    mRectF.right + mRadius - mDeltaD,
                    mRectF.bottom + mRadius - mDeltaD,//把手的长度比圆的半径小mDeltaD个像素
                    paint);

        } else {
            /**
             * 0~1  需要变换的范围
             * 0.5~1 mpro实际的变化范围
             * 转换公式：（mpro*2-1)
             */
            canvas.drawLine(
                    //使把手能够完全消失,最终画的把手的开始位置与结束位置左边相等，则把手消失，与底部横线重合
                    mRectF.right - mDeltaD + mRadius * (mpro * 2 - 1),
                    mRectF.bottom - mDeltaD + mRadius * (mpro * 2 - 1),
                    mRectF.right - mDeltaD + mRadius,
                    mRectF.bottom - mDeltaD + mRadius,
                    paint
            );
        }
        //绘制下面的横线
        canvas.drawLine(
                (mRectF.right - mDeltaD + mRadius) * (1 - mpro * 0.8f),//避免底部的横线画到最左边，这样能使底部的横线居中显示
                mRectF.bottom + mRadius - mDeltaD,
                mRectF.right + mRadius - mDeltaD,
                mRectF.bottom + mRadius - mDeltaD,
                paint
        );

        canvas.restore();
        //计算下一次绘制时，圆所在的矩形位置
        mRectF.left = mCircleX - mRadius + mpro * 250;
        mRectF.right = mCircleX + mRadius + mpro * 250;
        mRectF.top = mCircleY - mRadius;
        mRectF.bottom = mCircleY + mRadius;
    }


    /**
     * 恢复动画
     */
    public void revertAnimation(Paint paint, Canvas canvas) {
        canvas.save();
        if (mpro <= 0.5f) {//把手慢慢恢复
            //画把手
            canvas.drawLine(
                    mRectF.right + mDeltaD,
                    mRectF.bottom + mDeltaD,
                    mRectF.right + mRadius - mDeltaD,
                    mRectF.bottom + mRadius - mDeltaD,//把手的长度比圆的半径小mDeltaD个像素
                    paint);
        } else {
            //绘制圆
            canvas.drawArc(mRectF,
                    45,
                    -360 * mpro,//逆时针方向画圆弧
                    false,
                    paint);
            //画把手
            canvas.drawLine(
                    mRectF.right - mDeltaD,
                    mRectF.bottom - mDeltaD,
                    mRectF.right + mRadius - mDeltaD,
                    mRectF.bottom + mRadius - mDeltaD,//把手的长度比圆的半径小mDeltaD个像素
                    paint);
        }

        //画底部横线
        canvas.drawLine(
                mRectF.right - mRadius + mDeltaD,
                mRectF.bottom + mRadius - mDeltaD,
                (mRectF.right + mRadius) * mpro + mDeltaD,
                mRectF.bottom + mRadius - mDeltaD,
                paint
        );

        canvas.restore();
        //计算下一次绘制时，圆所在的矩形位置
        mRectF.left = mCircleX - mRadius - mpro * 250;
        mRectF.right = mCircleX + mRadius - mpro * 250;
        mRectF.top = mCircleY - mRadius;
        mRectF.bottom = mCircleY + mRadius;

    }

    @Override
    public void startAnim() {
        super.startAnim();
        mState = STATE_ANIM_START;
        startViewAnimation();
    }

    @Override
    public void resetAnim() {
        super.resetAnim();
        mState = STATE_ANIM_STOP;
        startViewAnimation();
    }

}
