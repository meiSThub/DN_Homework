package com.mei.test.ui.path.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.mei.test.R;

/**
 * Created by mei on 2017/7/18.
 * Description: qq消息气泡效果实现
 */
public class DragBubbleView extends View {

    /**
     * 气泡默认状态--静止
     */
    private final int BUBBLE_STATE_DEFAUL = 0;
    /**
     * 气泡相连
     */
    private final int BUBBLE_STATE_CONNECT = 1;
    /**
     * 气泡分离
     */
    private final int BUBBLE_STATE_APART = 2;
    /**
     * 气泡消失
     */
    private final int BUBBLE_STATE_DISMISS = 3;

    /**
     * 气泡半径
     */
    private float mBubbleRadius;
    //气泡颜色
    private int mBubbleColor;
    private String mTextStr;//气泡消息文字
    private int mTextColor;//气泡消息文字颜色
    private float mTextSize;//气泡消息文字大小
    private float mBubStillRadius;//不动气泡的半径
    private float mBubMoveableRadius;//可动气泡的半径
    private PointF mBubStillCenter;//不动气泡的圆心
    private PointF mBubMoveableCenter;//可动气泡的圆心
    private Paint mBubblePaint;//气泡的画笔
    private Path mBezierPath;//贝塞尔曲线Path
    private Paint mTextPaint;//文字画笔
    private Rect mTextRect;//
    private Paint mBurstPaint;//气泡爆炸效果的画笔
    private Rect mBurstRect;//

    private int mBubbleState = BUBBLE_STATE_DEFAUL;//气泡状态标志
    private float mDist;//两气泡圆心距离
    private float mMaxDist;//气泡相连状态最大圆心距离
    private float MOVE_OFFSET;//手指触摸偏移量
    private Bitmap[] mBurstBitmapsArray;//气泡爆炸的bitmap数组
    private boolean mIsBurstAnimStart = false;//是否在执行气泡爆炸动画
    private int mCurDrawableIndex;//当前气泡爆炸图片index
    /**
     * 气泡爆炸的图片id数组
     */
    private int[] mBurstDrawablesArray = {R.drawable.burst_1, R.drawable.burst_2
            , R.drawable.burst_3, R.drawable.burst_4, R.drawable.burst_5};

    public DragBubbleView(Context context) {
        this(context, null);
    }

    public DragBubbleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.DragBubbleView, defStyleAttr, 0);
        if (array != null) {
            mBubbleRadius = array.getDimension(R.styleable.DragBubbleView_bubble_radius, mBubbleRadius);
            mBubbleColor = array.getColor(R.styleable.DragBubbleView_bubble_color, Color.RED);
            mTextStr = array.getString(R.styleable.DragBubbleView_bubble_text);
            mTextSize = array.getDimension(R.styleable.DragBubbleView_bubble_textSize, mTextSize);
            mTextColor = array.getColor(R.styleable.DragBubbleView_bubble_textColor, Color.WHITE);
            array.recycle();
        }

        mBubStillRadius = mBubbleRadius;
        mBubMoveableRadius = mBubStillRadius;
        mMaxDist = 8 * mBubbleRadius;

        MOVE_OFFSET = mMaxDist / 4;

        //抗锯齿
        mBubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBubblePaint.setColor(mBubbleColor);
        mBubblePaint.setStyle(Paint.Style.FILL);
        mBezierPath = new Path();

        //气泡文字
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextRect = new Rect();

        //气泡爆炸效果
        mBurstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBurstPaint.setFilterBitmap(true);
        mBurstRect = new Rect();
        mBurstBitmapsArray = new Bitmap[mBurstDrawablesArray.length];

        for (int i = 0; i < mBurstDrawablesArray.length; i++) {
            //将气泡爆炸的drawable转为bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mBurstDrawablesArray[i]);
            mBurstBitmapsArray[i] = bitmap;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView(w, h);
    }

    /**
     * 初始化气泡的位置
     */
    private void initView(int w, int h) {
        if (mBubStillCenter == null) {
            mBubStillCenter = new PointF(w / 2, h / 2);
        } else {
            mBubStillCenter.set(w / 2, h / 2);
        }

        if (mBubMoveableCenter == null) {
            mBubMoveableCenter = new PointF(w / 2, h / 2);
        } else {
            mBubMoveableCenter.set(w / 2, h / 2);
        }

        mBubbleState = BUBBLE_STATE_DEFAUL;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (mBubbleState != BUBBLE_STATE_DISMISS) {
                    //计算两个气泡圆心的距离
                    mDist = (float) Math.hypot(event.getX() - mBubStillCenter.x, event.getY() - mBubStillCenter.y);
                    if (mDist < mBubbleRadius + MOVE_OFFSET) {
                        mBubbleState = BUBBLE_STATE_CONNECT;//// 加上MOVE_OFFSET是为了方便拖拽
                    } else {
                        mBubbleState = BUBBLE_STATE_DEFAUL;
                    }
                }
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                if (mBubbleState != BUBBLE_STATE_DEFAUL) {
                    mBubMoveableCenter.x = event.getX();
                    mBubMoveableCenter.y = event.getY();
                    mDist = (float) Math.hypot(event.getX() - mBubStillCenter.x, event.getY() - mBubStillCenter.y);
                    if (mBubbleState == BUBBLE_STATE_CONNECT) {
                        //减去MOVE_OFFSET是为了让不动气泡半径到一个较小值时就直接消失，或者说是进入分离状态
                        if (mDist < mMaxDist - MOVE_OFFSET) {
                            mBubStillRadius = mBubbleRadius - mDist / 8;
                        } else {
                            mBubbleState = BUBBLE_STATE_APART;
                        }
                    }
                    invalidate();
                }
            }
            break;
            case MotionEvent.ACTION_UP: {
                if (mBubbleState == BUBBLE_STATE_CONNECT) {
                    startBubbleRestAnim();
                } else if (mBubbleState == BUBBLE_STATE_APART) {
                    if (mDist < 2 * mBubbleRadius) {
                        startBubbleRestAnim();
                    } else {
                        startBubbleBurstAnim();
                    }
                }
            }

            break;
        }
        return true;
    }

    /**
     * 气泡爆炸动画
     */
    private void startBubbleBurstAnim() {
        mBubbleState = BUBBLE_STATE_DISMISS;
        mIsBurstAnimStart = true;
        //做一个int型属性动画，从0~mBurstDrawablesArray.length结束
        ValueAnimator animator = ValueAnimator.ofInt(0, mBurstDrawablesArray.length);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurDrawableIndex = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mIsBurstAnimStart = false;
            }
        });
        animator.start();
    }

    /**
     *
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startBubbleRestAnim() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointFEvaluator(),
                new PointF(mBubMoveableCenter.x, mBubMoveableCenter.y),
                new PointF(mBubStillCenter.x, mBubStillCenter.y));
        animator.setDuration(200);
        animator.setInterpolator(new OvershootInterpolator(5f));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBubMoveableCenter = (PointF) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBubbleState = BUBBLE_STATE_DEFAUL;
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 1、画静止状态
        // 2、画相连状态
        // 3、画分离状态
        // 4、画消失状态---爆炸动画

        // 1、画拖拽的气泡 和 文字
        if (mBubbleState != BUBBLE_STATE_DISMISS) {//只要状态不是消失的状态，就都需要画文字和拖拽的气泡
            canvas.drawCircle(mBubMoveableCenter.x, mBubMoveableCenter.y, mBubMoveableRadius, mBubblePaint);
            if (mTextStr != null) {
                mTextPaint.getTextBounds(mTextStr, 0, mTextStr.length(), mTextRect);
                canvas.drawText(mTextStr, mBubMoveableCenter.x - mTextRect.width() / 2,
                        mBubMoveableCenter.y + mTextRect.height() / 2, mTextPaint);
            }
        }
        // 2、画相连的气泡状态
        if (mBubbleState == BUBBLE_STATE_CONNECT) {
            //1、画静止气泡
            canvas.drawCircle(mBubStillCenter.x, mBubStillCenter.y, mBubStillRadius, mBubblePaint);
            // 2、画相连曲线
            // 计算控制点坐标，两个圆心的中点
            int iAnchorX = (int) ((mBubStillCenter.x + mBubMoveableCenter.x) / 2);
            int iAnchorY = (int) ((mBubStillCenter.y + mBubMoveableCenter.y) / 2);

            float cosTheta = (mBubMoveableCenter.x - mBubStillCenter.x) / mDist;
            float sinTheta = (mBubMoveableCenter.y - mBubStillCenter.y) / mDist;

            float iBubStillStartX = mBubStillCenter.x - mBubStillRadius * sinTheta;
            float iBubStillStartY = mBubStillCenter.y + mBubStillRadius * cosTheta;
            float iBubMoveableEndX = mBubMoveableCenter.x - mBubMoveableRadius * sinTheta;
            float iBubMoveableEndY = mBubMoveableCenter.y + mBubMoveableRadius * cosTheta;
            float iBubMoveableStartX = mBubMoveableCenter.x + mBubMoveableRadius * sinTheta;
            float iBubMoveableStartY = mBubMoveableCenter.y - mBubMoveableRadius * cosTheta;
            float iBubStillEndX = mBubStillCenter.x + mBubStillRadius * sinTheta;
            float iBubStillEndY = mBubStillCenter.y - mBubStillRadius * cosTheta;

            mBezierPath.reset();
            //画上半弧
            mBezierPath.moveTo(iBubStillStartX, iBubStillStartY);
            mBezierPath.quadTo(iAnchorX, iAnchorY, iBubMoveableEndX, iBubMoveableEndY);
            //画下半弧
            mBezierPath.lineTo(iBubMoveableStartX, iBubMoveableStartY);
            mBezierPath.quadTo(iAnchorX, iAnchorY, iBubStillEndX, iBubStillEndY);
            mBezierPath.close();
            canvas.drawPath(mBezierPath, mBubblePaint);
        }

        // 3、画消失状态---爆炸动画
        if (mIsBurstAnimStart) {
            mBurstRect.set((int) (mBubMoveableCenter.x - mBubMoveableRadius),
                    (int) (mBubMoveableCenter.y - mBubMoveableRadius),
                    (int) (mBubMoveableCenter.x + mBubMoveableRadius),
                    (int) (mBubMoveableCenter.y + mBubMoveableRadius));
            canvas.drawBitmap(mBurstBitmapsArray[mCurDrawableIndex], null, mBurstRect, mBubblePaint);
        }
    }

    public void reset() {
        initView(getWidth(), getHeight());
        invalidate();
    }
}
