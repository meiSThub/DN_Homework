package com.mei.test;

import com.mei.test.autofit.AutoFitMainActivity;
import com.mei.test.base.BaseListActivity;
import com.mei.test.optimize.OptimizeMainActivity;
import com.mei.test.test.TestActivity;
import com.mei.test.ui.MaterialDesign.MaterialDesignMainActivity;
import com.mei.test.ui.animation.AnimationActivity;
import com.mei.test.ui.canvas.CanvasMainActivity;
import com.mei.test.ui.customview.CustomViewMainActivity;
import com.mei.test.ui.filter.FilterActivity;
import com.mei.test.ui.flowlayout.FlowLayoutActivity;
import com.mei.test.ui.paint.PaintMainActivity;
import com.mei.test.ui.palatte.PaletteActivity;
import com.mei.test.ui.path.PathMainActivity;
import com.mei.test.ui.svg.TaiWanMapActivity;
import com.mei.test.ui.xfermode.XfermodeMainActivity;

import java.util.Map;


public class MainActivity extends BaseListActivity {
    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("测试", TestActivity.class);
        dataMaps.put("手写流式布局", FlowLayoutActivity.class);
        dataMaps.put("Paint的用法", PaintMainActivity.class);
        dataMaps.put("xfermode的使用", XfermodeMainActivity.class);
        dataMaps.put("滤镜效果——矩阵的使用", FilterActivity.class);
        dataMaps.put("Canvas的使用", CanvasMainActivity.class);
        dataMaps.put("Path的用法", PathMainActivity.class);
        dataMaps.put("SVG画可交互的台湾地图", TaiWanMapActivity.class);
        dataMaps.put("动画的基本使用", AnimationActivity.class);
        dataMaps.put("Palette调色板的使用", PaletteActivity.class);
        dataMaps.put("自定义View", CustomViewMainActivity.class);
        dataMaps.put("屏幕适配", AutoFitMainActivity.class);
        dataMaps.put("MeterialDesign相关API的使用", MaterialDesignMainActivity.class);
        dataMaps.put("性能优化", OptimizeMainActivity.class);

    }
}
