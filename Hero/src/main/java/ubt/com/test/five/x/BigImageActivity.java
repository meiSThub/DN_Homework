package ubt.com.test.five.x;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import ubt.com.test.R;

/**
 * 展示原图activity
 */
public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_big_image);
    }
}
