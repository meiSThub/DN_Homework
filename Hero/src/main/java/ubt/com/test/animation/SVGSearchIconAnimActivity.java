package ubt.com.test.animation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ubt.com.test.R;

public class SVGSearchIconAnimActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Drawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_icon_svganim);

        mImageView = (ImageView) findViewById(R.id.image_view);
    }

    public void onClick(View v) {

        mDrawable = mImageView.getDrawable();

        if (mDrawable instanceof Animatable) {
            ((Animatable) mDrawable).start();
        }
    }
}
