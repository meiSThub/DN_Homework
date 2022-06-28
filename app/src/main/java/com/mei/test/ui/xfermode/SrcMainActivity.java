package com.mei.test.ui.xfermode;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;

/**
 * Created by mei on 2017/6/19.
 * Description:
 */
public class SrcMainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_src_main);
        findViewById(R.id.roundsrcin).setOnClickListener(this);
        findViewById(R.id.invertsrcin).setOnClickListener(this);
        findViewById(R.id.eraserview).setOnClickListener(this);
        findViewById(R.id.guaguaview).setOnClickListener(this);
        findViewById(R.id.roundsrcatop).setOnClickListener(this);
        findViewById(R.id.invertsrcatop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()) {
            case R.id.roundsrcin:
                findViewById(R.id.roundsrcin_view).setVisibility(View.VISIBLE);
                break;
            case R.id.invertsrcin:
                findViewById(R.id.invertsrcin_view).setVisibility(View.VISIBLE);
                break;
            case R.id.eraserview:
                findViewById(R.id.eraserview_view).setVisibility(View.VISIBLE);
                break;
            case R.id.guaguaview:
                findViewById(R.id.guaguaview_view).setVisibility(View.VISIBLE);
                break;
            case R.id.roundsrcatop:
                findViewById(R.id.roundsrcatop_view).setVisibility(View.VISIBLE);
                break;
            case R.id.invertsrcatop:
                findViewById(R.id.invertsrcatop_view).setVisibility(View.VISIBLE);
                break;
        }

    }

    private void hideAllViews() {
        findViewById(R.id.roundsrcin_view).setVisibility(View.GONE);
        findViewById(R.id.invertsrcin_view).setVisibility(View.GONE);
        findViewById(R.id.eraserview_view).setVisibility(View.GONE);
        findViewById(R.id.guaguaview_view).setVisibility(View.GONE);
        findViewById(R.id.roundsrcatop_view).setVisibility(View.GONE);
        findViewById(R.id.invertsrcatop_view).setVisibility(View.GONE);
    }

    @Override
    public String getItemTitle() {
        return null;
    }
}
