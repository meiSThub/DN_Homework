package com.mei.test.ui.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;

/**
 * Created by mei on 2017/6/9.
 * Description:动画练习类
 */
public class AnimationActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mImageView = (ImageView) findViewById(R.id.img_btn);
        mImageView.setOnClickListener(this);
    }

    @Override
    public String getItemTitle() {
        return "动画基础练习";
    }

    @Override
    public void onClick(View v) {

//        doPaoWuXian(v);
        doInterpolator(v);
    }

    /**
     * 实现抛物线效果，（购物车，股指数）
     * x:匀速
     * y:加速度y=1/2*g*t*t;利用估值器实现
     */
    private void doPaoWuXian(View v) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1000);
        valueAnimator.setObjectValues(new PointF(0, 0));//起点位置
        final PointF pointF = new PointF();
        valueAnimator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                //估值计算方法，在执行的过程当中干预改变属性的值

                //不断的去计算并修改坐标
                //x匀速运动x=v*t;,为了看起来效果好，我们让t变成fraction*5
                pointF.x = 100f * fraction * 5;
                //y=vt=1/2*g*t*t  10只是为了效果更好，让y变化更快一点
                pointF.y = 10f * 0.5f * 9.8f * (fraction * 5) * (fraction * 5);
                return pointF;
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF f = (PointF) animation.getAnimatedValue();
                mImageView.setX(f.x);
                mImageView.setY(f.y);
            }
        });
        valueAnimator.start();
    }

    /**
     * 差之器
     *
     * @param v
     */
    private void doInterpolator(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", 0f, 500f);

        animator.setDuration(500);
//        animator.setInterpolator(new AccelerateInterpolator());//加速变化
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());//先jia加速后减速变化
//        animator.setInterpolator(new BounceInterpolator());//掉下回弹效果
        animator.setInterpolator(new AnticipateInterpolator());//掉下回弹效果

        animator.start();
    }
}
