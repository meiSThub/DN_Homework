package com.mei.test.ui.paint;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;

/**
 * Created by mei on 2017/5/28.
 * Description：Paint的高级渲染
 */
public class PaintHighActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_high);
//        MyGradientView gradientView=new MyGradientView(this);
//        ZoomImageView zoomImageView = new ZoomImageView(this);
//        setContentView(zoomImageView);
    }

    @Override
    public String getItemTitle() {
        return "Paint的高级渲染";
    }
}
