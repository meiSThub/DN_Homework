package ubt.com.test.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import ubt.com.test.R;

/**
 * 旋转收缩动画
 */
public class RotateMenuActivity extends AppCompatActivity {

    private ImageView mImageView0;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_menu);

        mImageView0 = (ImageView) findViewById(R.id.img1);
        mImageView1 = (ImageView) findViewById(R.id.img2);
        mImageView2 = (ImageView) findViewById(R.id.img3);
        mImageView3 = (ImageView) findViewById(R.id.img4);
        mImageView4 = (ImageView) findViewById(R.id.img5);
    }

    boolean mIsOpen = false;

    public void onClick(View v) {
        if (mIsOpen) {
            mIsOpen = false;
            closeAnimation();
        } else {
            mIsOpen = true;
            openAnimation();
        }
    }

    public void openAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageView0, "alpha", 1f, 0.5f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageView1, "translationX", -dip2px(70));
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageView2, "translationY", -dip2px(70));
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageView3, "translationX", dip2px(70));
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(mImageView4, "translationY", dip2px(70));

        ObjectAnimator animator6 = ObjectAnimator.ofFloat(mImageView1, "rotation", 360);
        ObjectAnimator animator7 = ObjectAnimator.ofFloat(mImageView2, "rotation", 360);
        ObjectAnimator animator8 = ObjectAnimator.ofFloat(mImageView3, "rotation", 360);
        ObjectAnimator animator9 = ObjectAnimator.ofFloat(mImageView4, "rotation", 360);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator1, animator2, animator3, animator4, animator5, animator6,
                animator7,
                animator8,
                animator9);
        set.start();
    }

    public void closeAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageView0, "alpha", 0.5f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageView1, "translationX", 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageView2, "translationY", 0);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageView3, "translationX", 0);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(mImageView4, "translationY", 0);

        ObjectAnimator animator6 = ObjectAnimator.ofFloat(mImageView1, "rotation", 0);
        ObjectAnimator animator7 = ObjectAnimator.ofFloat(mImageView2, "rotation", 0);
        ObjectAnimator animator8 = ObjectAnimator.ofFloat(mImageView3, "rotation", 0);
        ObjectAnimator animator9 = ObjectAnimator.ofFloat(mImageView4, "rotation", 0);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator1, animator2, animator3, animator4, animator5,
                animator6,
                animator7,
                animator8,
                animator9);
        set.start();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
