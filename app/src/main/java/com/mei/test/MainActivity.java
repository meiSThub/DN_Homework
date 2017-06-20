package com.mei.test;

import com.mei.test.base.BaseListActivity;
import com.mei.test.ui.animation.AnimationActivity;
import com.mei.test.ui.paint.PaintMainActivity;
import com.mei.test.ui.svg.TaiWanMapActivity;
import com.mei.test.ui.xfermode.XfermodeMainActivity;

import java.util.Map;


public class MainActivity extends BaseListActivity {
    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("Paint的用法", PaintMainActivity.class);
        dataMaps.put("Path的用法", PaintMainActivity.class);
        dataMaps.put("xfermode的使用", XfermodeMainActivity.class);
        dataMaps.put("SVG画可交互的台湾地图", TaiWanMapActivity.class);
        dataMaps.put("动画的基本使用", AnimationActivity.class);
    }
}
