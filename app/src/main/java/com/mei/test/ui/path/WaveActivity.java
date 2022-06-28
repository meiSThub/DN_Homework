package com.mei.test.ui.path;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.path.widget.WaveView;

/**
 * Created by mei on 2017/7/13.
 * Description:充电水波纹动画
 */
public class WaveActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WaveView view = new WaveView(this);
        setContentView(view);
    }
}
