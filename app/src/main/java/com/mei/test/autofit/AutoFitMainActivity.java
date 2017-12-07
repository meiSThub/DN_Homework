package com.mei.test.autofit;

import com.mei.test.autofit.case3.AutoFit3Activity;
import com.mei.test.base.BaseListActivity;

import java.util.Map;

/**
 * Created by mei on 2017/11/25.
 * Description:
 */

public class AutoFitMainActivity extends BaseListActivity {

    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("屏幕适配", AutoFit3Activity.class);
    }
}
