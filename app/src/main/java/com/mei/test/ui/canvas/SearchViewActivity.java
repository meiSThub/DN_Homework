package com.mei.test.ui.canvas;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;
import com.mei.test.ui.canvas.widget.search.Controller1;
import com.mei.test.ui.canvas.widget.search.SearchView;

/**
 * Created by mei on 2017/7/4.
 * Description:搜索动画
 */
public class SearchViewActivity extends BaseActivity {

    private SearchView mSearchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        mSearchView = (SearchView) findViewById(R.id.sv);
        mSearchView.setController(new Controller1());

    }

    public void start(View v) {
        mSearchView.startAnimation();
    }

    public void reset(View v) {
        mSearchView.resetAnimation();
    }
}
