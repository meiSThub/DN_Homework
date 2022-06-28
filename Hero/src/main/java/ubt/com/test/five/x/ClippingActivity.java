package ubt.com.test.five.x;

import android.graphics.Outline;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;

import ubt.com.test.R;

/**
 * Clipping裁剪
 */
public class ClippingActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipping);

        View v1 = findViewById(R.id.tv_rect);

        View v2 = findViewById(R.id.tv_circle);
        //定义outline
        ViewOutlineProvider provider1 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
            }
        };

        ViewOutlineProvider provider2 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };

        v1.setOutlineProvider(provider1);
        v2.setOutlineProvider(provider2);
    }
}
