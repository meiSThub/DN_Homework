package com.mei.test.ui.customview;

import com.mei.test.base.BaseListActivity;

import java.util.Map;

/**
 * Created by mei on 2017/7/20.
 * Description:自定义View
 */
public class CustomViewMainActivity extends BaseListActivity {

    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("自定义ImageView", ImageViewActivity.class);
        dataMaps.put("自定义双图片带文字的ImageView", DoubleImageViewActivity.class);
        dataMaps.put("自定义组合控件", CustomTextViewActivity.class);
    }


}
