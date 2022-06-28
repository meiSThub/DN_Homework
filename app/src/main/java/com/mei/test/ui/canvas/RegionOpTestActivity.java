package com.mei.test.ui.canvas;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.canvas.widget.RegionOpTestView;

/**
 * Created by mei on 2017/10/24.
 * Description:
 */

public class RegionOpTestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RegionOpTestView(this));
    }
}
