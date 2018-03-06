package ubt.com.test.five.x;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import ubt.com.test.R;

/**
 * 从TransitionAnimation跳转到此类
 */
public class TransitionDestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getIntExtra("flag", 0);
        switch (flag) {
            case 0:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setEnterTransition(new Explode());
                    getWindow().setExitTransition(new Explode());
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setEnterTransition(new Slide());
                    getWindow().setExitTransition(new Slide());
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setEnterTransition(new Fade());
                        getWindow().setExitTransition(new Fade());
                    }
                }
                break;
            case 3:
                break;

        }
        setContentView(R.layout.activity_transition_destination);
    }
}
