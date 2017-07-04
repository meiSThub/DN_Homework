package com.mei.test.ui.canvas;

import com.mei.test.base.BaseListActivity;

import java.util.Map;

/**
 * Created by mei on 2017/6/25.
 * Description:Canvas主类
 */
public class CanvasMainActivity extends BaseListActivity {

    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("Canvas的基本使用", CanvasBaseActivity.class);
        dataMaps.put("图片的叠加", RevealActivity.class);
        dataMaps.put("搜索按钮动画", SearchViewActivity.class);
    }
}
