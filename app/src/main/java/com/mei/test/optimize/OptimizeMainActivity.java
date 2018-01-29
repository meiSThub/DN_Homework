package com.mei.test.optimize;

import com.mei.test.base.BaseListActivity;
import com.mei.test.optimize.bitmap.BitmapMemoryOptimizeActivity;

import java.util.Map;

/**
 * 性能优化:
 */
public class OptimizeMainActivity extends BaseListActivity {

    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("Bitmap内存优化", BitmapMemoryOptimizeActivity.class);
    }
}
