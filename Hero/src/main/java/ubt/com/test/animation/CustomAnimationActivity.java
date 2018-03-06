package ubt.com.test.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ubt.com.test.R;
import ubt.com.test.animation.widget.CustomAnimation;

/**
 * 自定义动画：3D动画
 */
public class CustomAnimationActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_animation);

        mButton = (Button) findViewById(R.id.btn_start);
    }

    public void onClick(View v) {
        CustomAnimation animation = new CustomAnimation();
        animation.setRotateY(20);
        mButton.startAnimation(animation);
    }
}
