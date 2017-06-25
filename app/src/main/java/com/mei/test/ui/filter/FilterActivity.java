package com.mei.test.ui.filter;

import android.os.Bundle;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.filter.widget.FilterView;

/**
 * Created by mei on 2017/6/20.
 * Description:滤镜效果
 */
public class FilterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        FilterView view = new FilterView(this);
        setContentView(view);
    }
}
