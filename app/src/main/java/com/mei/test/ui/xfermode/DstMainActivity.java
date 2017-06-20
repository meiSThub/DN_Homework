package com.mei.test.ui.xfermode;

import android.os.Bundle;
import android.view.View;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;

/**
 * Created by mei on 2017/6/20.
 * Description:
 */
public class DstMainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dst_main);

        findViewById(R.id.rounddstinbtn).setOnClickListener(this);
        findViewById(R.id.invertdstinbtn).setOnClickListener(this);
        findViewById(R.id.irregularwavebtn).setOnClickListener(this);
        findViewById(R.id.heartbitbtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()) {
            case R.id.rounddstinbtn:
                findViewById(R.id.roundimage).setVisibility(View.VISIBLE);
                break;
            case R.id.invertdstinbtn:
                findViewById(R.id.invertimg).setVisibility(View.VISIBLE);
                break;
            case R.id.irregularwavebtn:
                findViewById(R.id.irregularwaveview).setVisibility(View.VISIBLE);
                break;
            case R.id.heartbitbtn:
                findViewById(R.id.heartbitview).setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideAllViews() {
        findViewById(R.id.roundimage).setVisibility(View.GONE);
        findViewById(R.id.invertimg).setVisibility(View.GONE);
        findViewById(R.id.irregularwaveview).setVisibility(View.GONE);
        findViewById(R.id.heartbitview).setVisibility(View.GONE);
    }

    @Override
    public String getItemTitle() {
        return null;
    }
}
