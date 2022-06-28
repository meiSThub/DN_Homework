package com.mei.test.ui.path;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.path.widget.GarbageView;

/**
 * Created by mei on 2017/7/13.
 * Description:垃圾桶动画
 */
public class GarbageActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GarbageView view = new GarbageView(this);
        setContentView(view);
    }
}
