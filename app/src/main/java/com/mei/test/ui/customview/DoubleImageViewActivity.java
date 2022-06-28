package com.mei.test.ui.customview;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;

/**
 * Created by mei on 2017/7/20.
 * Description:自定义 继承控件
 */
public class DoubleImageViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_image_view);
    }
}
