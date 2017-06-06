package com.mei.test.ui.paint;

import android.os.Bundle;
import android.view.View;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;
import com.mei.test.ui.paint.widget.RadarView;
import com.mei.test.ui.paint.widget.RadarView2;

/**
 * Created by VST on 2017/6/6.
 * Description: 雷达扫描
 */

public class RadarActivity extends BaseActivity {


    private RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RadarView view = new RadarView(this);
//        setContentView(view);
        setContentView(R.layout.activity_radar);

        mRadarView = (RadarView) findViewById(R.id.radarview);
    }

    public void start(View view) {
        mRadarView.startScan();
    }

    public void stop(View view) {
        mRadarView.stopScan();
    }
}
