package ubt.com.test.animation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ubt.com.test.R;

public class SVGLinearImgActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Drawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svglinear_img);

        mImageView = (ImageView) findViewById(R.id.image_view);

    }

    public void onClick(View v) {

        mDrawable = mImageView.getDrawable();

        if (mDrawable instanceof Animatable) {
            ((Animatable) mDrawable).start();
        }
    }
}
