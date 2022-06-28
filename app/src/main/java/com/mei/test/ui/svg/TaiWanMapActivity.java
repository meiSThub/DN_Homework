package com.mei.test.ui.svg;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.svg.widget.TaiWanMapView;

/**
 * Created by mei on 2017/6/1.
 * Description: 通过SVG画交互式台湾地图
 */
public class TaiWanMapActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaiWanMapView mapView = new TaiWanMapView(this);
        setContentView(mapView);
    }

    @Override
    public String getItemTitle() {
        return "SVG画可交互的台湾地图";
    }
}
