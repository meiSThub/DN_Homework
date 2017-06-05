package com.mei.test.ui.path;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.path.widget.PathFillView;

/**
 * Created by mei on 2017/5/23.
 * Description:
 */
public class PathActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PathFillView fillView = new PathFillView(this);
        setContentView(fillView);
    }
}
