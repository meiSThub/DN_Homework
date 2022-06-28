package ubt.com.test.five.x;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import ubt.com.test.R;

/**
 * Activity跳转的转场动画
 */
public class TransitionAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_animation);
    }

    private Intent mIntent;

    public void explode(View view) {
        mIntent = new Intent(this, TransitionDestinationActivity.class);
        mIntent.putExtra("flag", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            //startActivity(mIntent);
        }
    }

    public void slide(View view) {
        mIntent = new Intent(this, TransitionDestinationActivity.class);
        mIntent.putExtra("flag", 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            //startActivity(mIntent);
        }

    }

    public void fade(View view) {
        mIntent = new Intent(this, TransitionDestinationActivity.class);
        mIntent.putExtra("flag", 2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            //startActivity(mIntent);
        }

    }

    public void share(View view) {
        mIntent = new Intent(this, TransitionDestinationActivity.class);
        mIntent.putExtra("flag", 3);
        View fab = findViewById(R.id.fab_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this, view, "share").toBundle());
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair.create(view, "share"),//创建多个共享元素
                    Pair.create(fab, "fab")).toBundle());
        }

    }

    public void showBigImg(View view) {
        mIntent = new Intent(this, BigImageActivity.class);
        View shareView = findViewById(R.id.img_button);
        startActivity(mIntent,
                ActivityOptions.makeSceneTransitionAnimation(this, shareView, "source_img").toBundle());
    }
}
