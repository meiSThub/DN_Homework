package ubt.com.test.animation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import ubt.com.test.R;

/**
 * 通过SVG矢量动画模拟实现地球围绕太阳转，月亮围绕地球转
 */
public class SVGSunSystemAnimActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Drawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun_system);

        mImageView = (ImageView) findViewById(R.id.image_view);

        mDrawable = mImageView.getDrawable();

        if (mDrawable instanceof Animatable) {
            ((Animatable) mDrawable).start();
        }

    }
}
