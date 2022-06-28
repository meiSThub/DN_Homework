package ubt.com.test.five.x;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import ubt.com.test.R;

/**
 * AnimatorSelector的使用
 * AnimatorSelector:一个状态改变的动画效果的selector
 */
public class AnimatorSelectorActivity extends AppCompatActivity {

    private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};
    private ImageView mImageView;
    private boolean mIsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_selector);
        mImageView = (ImageView) findViewById(R.id.img_checked);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsCheck) {
                    mIsCheck = false;
                    mImageView.setImageState(STATE_UNCHECKED, true);
                } else {
                    mImageView.setImageState(STATE_CHECKED, true);
                    mIsCheck = true;
                }
            }
        });
    }
}
