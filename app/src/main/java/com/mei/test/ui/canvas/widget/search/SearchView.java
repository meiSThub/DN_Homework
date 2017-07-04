package com.mei.test.ui.canvas.widget.search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mei on 2017/6/29.
 * Description:搜索图标动画
 */
public class SearchView extends View {

    private Paint mPaint;
    private BaseController mController;

    public SearchView(Context context) {
        super(context);
        init();
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
    }

    public void setController(BaseController controller) {
        mController = controller;
        mController.setSearchView(this);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mController.draw(canvas, mPaint);
    }

    public void startAnimation() {
        if (mController != null) {
            mController.startAnim();
        }
    }

    public void resetAnimation() {
        if (mController != null) {
            mController.resetAnim();
        }
    }
}
