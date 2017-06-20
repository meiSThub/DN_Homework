package com.mei.test.ui.paint;

import com.mei.test.base.BaseListActivity;

import java.util.Map;

/**
 * Created by mei on 2017/6/20.
 * Description:
 */
public class PaintMainActivity extends BaseListActivity {
    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("Paint基本使用--画小黄人", MinionActivity.class);
        dataMaps.put("Paint高级渲染", PaintHighActivity.class);
        dataMaps.put("Paint:雷达扫描", RadarActivity.class);
        dataMaps.put("Paint:水波纹按钮", RippleActivity.class);
        dataMaps.put("Paint:倒影图片", InvertedImageActivity.class);
    }
}
