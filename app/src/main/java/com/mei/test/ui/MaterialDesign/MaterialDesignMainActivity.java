package com.mei.test.ui.MaterialDesign;

import com.mei.test.base.BaseListActivity;

import java.util.Map;

/**
 * MeterialDesign相关API的使用
 */
public class MaterialDesignMainActivity extends BaseListActivity {

    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("ItemTouchHelper的使用", ItemTouchHelperActivity.class);
    }
}
