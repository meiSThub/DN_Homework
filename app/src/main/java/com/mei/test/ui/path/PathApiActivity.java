package com.mei.test.ui.path;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.path.widget.PathApiView;

/**
 * Created by mei on 2017/10/25.
 * Description:
 */

public class PathApiActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathApiView(this));
    }
}
