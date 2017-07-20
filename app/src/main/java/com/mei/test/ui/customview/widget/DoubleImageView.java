package com.mei.test.ui.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.mei.test.R;

/**
 * Created by mei on 2017/7/20.
 * Description:
 */
public class DoubleImageView extends ImageView {

    /*Image Contents*/
    private Drawable mLeftDrawable, mRightDrawable;//图片
    /*Text Contents*/
    private CharSequence mText;//文字内容
    private StaticLayout mTextLayout;//文字占用的空间
    /*Text Drawing*/
    private TextPaint mTextPaint;//画文字用的画笔
    private Point mTextOrigin;//文字的坐标起始位置
    private int mSpacing;//文字与图片之间的距离

    public DoubleImageView(Context context) {
        this(context, null);
    }

    public DoubleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextOrigin = new Point(0, 0);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DoubleImageView, defStyleAttr, 0);
        if (array != null) {
            Drawable leftDrawable = array.getDrawable(R.styleable.DoubleImageView_android_drawableLeft);
            if (leftDrawable != null) {
                setLeftDrawable(leftDrawable);
            }

            Drawable rightDrawable = array.getDrawable(R.styleable.DoubleImageView_android_drawableRight);
            if (rightDrawable != null) {
                setRightDrawable(rightDrawable);
            }

            int spacing = array.getDimensionPixelOffset(R.styleable.DoubleImageView_android_spacing, 0);
            setSpacing(spacing);

            int color = array.getColor(R.styleable.DoubleImageView_android_textColor, 0);
            mTextPaint.setColor(color);

            int textSize = array.getDimensionPixelSize(R.styleable.DoubleImageView_android_textSize, 0);
            mTextPaint.setTextSize(textSize);

            CharSequence text = array.getText(R.styleable.DoubleImageView_android_text);
            setText(text);
            array.recycle();
        }
    }


    public void setSpacing(int spacing) {
        mSpacing = spacing;
        updateContentBounds();
        invalidate();
    }

    public void setText(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            mText = text;
            updateContentBounds();
            invalidate();
        }
    }

    public void setLeftDrawableResource(int resId) {
        Drawable d = getResources().getDrawable(resId);
        setLeftDrawable(d);
    }

    public void setLeftDrawable(Drawable leftDrawable) {
        mLeftDrawable = leftDrawable;
        updateContentBounds();
        invalidate();
    }


    public void setRightDrawableResource(int resId) {
        Drawable d = getResources().getDrawable(resId);
        setRightDrawable(d);
    }

    public void setRightDrawable(Drawable rightDrawable) {
        mRightDrawable = rightDrawable;
        updateContentBounds();
        invalidate();
    }

    //确定边界点
    private void updateContentBounds() {
        if (mText == null) mText = "";
        float textWidth = mTextPaint.measureText(mText, 0, mText.length());
        mTextLayout = new StaticLayout(mText, mTextPaint, (int) textWidth, Layout.Alignment.ALIGN_CENTER, 1f, 0f, true);
        int left = (getWidth() - getDesiredWidth()) / 2;
        int top = (getHeight() - getDesiredHeight()) / 2;

        if (mLeftDrawable != null) {
            mLeftDrawable.setBounds(left, top, left + mLeftDrawable.getIntrinsicWidth(), top + mLeftDrawable.getIntrinsicHeight());
            left += mLeftDrawable.getIntrinsicWidth() * 0.33f;
            top += mLeftDrawable.getIntrinsicHeight() * 0.33f;
        }


        if (mRightDrawable != null) {
            mRightDrawable.setBounds(left, top, left + mRightDrawable.getIntrinsicWidth(), top + mRightDrawable.getIntrinsicHeight());
            left = mRightDrawable.getBounds().right + mSpacing;
        }

        if (mTextLayout != null) {
            top = (getHeight() - mTextLayout.getHeight()) / 2;
            mTextOrigin.set(left, top);
        }
    }

    /**
     * 计算整个View的宽
     */
    public int getDesiredWidth() {
        int leftWidth;
        if (mLeftDrawable == null) {
            leftWidth = 0;
        } else {
            leftWidth = mLeftDrawable.getIntrinsicWidth();
        }

        int rightWidth;
        if (mRightDrawable == null) {
            rightWidth = 0;
        } else {
            rightWidth = mRightDrawable.getIntrinsicWidth();
        }

        int textWidth;
        if (mTextLayout == null) {
            textWidth = 0;
        } else {
            textWidth = mTextLayout.getWidth();
        }

        return (int) (leftWidth * 0.67 + rightWidth * 0.67) + mSpacing + textWidth;
    }

    /**
     * 计算整个View的高
     */
    public int getDesiredHeight() {
        int leftHeight;
        if (mLeftDrawable == null) {
            leftHeight = 0;
        } else {
            leftHeight = mLeftDrawable.getIntrinsicHeight();
        }

        int rightHeight;
        if (mRightDrawable == null) {
            rightHeight = 0;
        } else {
            rightHeight = mRightDrawable.getIntrinsicHeight();
        }

        return (int) (leftHeight * 0.67 + rightHeight * 0.67);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = getDesiredWidth();
        int desiredHeight = getDesiredHeight();

        int width;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthSpecMode) {
            case MeasureSpec.AT_MOST:
                width = Math.min(desiredWidth, widthSpecSize);
                break;
            case MeasureSpec.EXACTLY:
                width = widthSpecSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                width = desiredWidth;
                break;
        }

        int height;
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightSpecMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(desiredHeight, heightSpecSize);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSpecSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                height = desiredHeight;
                break;
        }
        setMeasuredDimension(width, height);
//        super.onMeasure(MeasureSpec.makeMeasureSpec(width, widthSpecMode), MeasureSpec.makeMeasureSpec(height, heightSpecMode));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            updateContentBounds();
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mLeftDrawable != null) {
            mLeftDrawable.draw(canvas);
        }

        if (mRightDrawable != null) {
            mRightDrawable.draw(canvas);
        }

        if (mTextLayout != null) {
            canvas.save();
            canvas.translate(mTextOrigin.x, mTextOrigin.y);
            mTextLayout.draw(canvas);
            canvas.restore();
        }
    }
}
