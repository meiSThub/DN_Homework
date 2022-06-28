package com.mei.test.ui.canvas;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.canvas.widget.CanvasBaseView;

/**
 * Created by mei on 2017/6/25.
 * Description:Canvas的基本使用
 */
public class CanvasBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CanvasBaseView view = new CanvasBaseView(this);
        setContentView(view);
    }
}
