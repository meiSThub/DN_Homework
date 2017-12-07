package com.mei.test.ui.path;

import com.mei.test.base.BaseListActivity;

import java.util.Map;

/**
 * Created by mei on 2017/6/20.
 * Description:
 */
public class PathMainActivity extends BaseListActivity {
    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("Path的setFillType方法的使用", PathActivity.class);
        dataMaps.put("Path的op方法的使用", PathOpActivity.class);
        dataMaps.put("二阶贝塞尔曲线演示", CurveViewActivity.class);
        dataMaps.put("垃圾桶动画", GarbageActivity.class);
        dataMaps.put("充电水波纹动画", WaveActivity.class);
        dataMaps.put("qq消息气泡的实现", DragBubbleActivity.class);
        dataMaps.put("Path基础Api练习", PathApiActivity.class);
    }
}
