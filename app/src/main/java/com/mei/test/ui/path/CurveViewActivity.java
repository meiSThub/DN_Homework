package com.mei.test.ui.path;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.path.widget.CurveView;

/**
 * Created by mei on 2017/7/13.
 * Description:二阶贝塞尔曲线示例
 */
public class CurveViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurveView view = new CurveView(this);
        setContentView(view);
    }
}
