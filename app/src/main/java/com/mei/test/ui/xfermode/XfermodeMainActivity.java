package com.mei.test.ui.xfermode;

import com.mei.test.base.BaseListActivity;

import java.util.Map;

/**
 * Created by mei on 2017/6/20.
 * Description:xfermode的主类
 */
public class XfermodeMainActivity extends BaseListActivity {

    @Override
    public void initDatas(Map<String, Class> dataMaps) {
        dataMaps.put("xfermode的使用", XfermodeActivity.class);
        dataMaps.put("xfermode的SRC模式的使用", SrcMainActivity.class);
        dataMaps.put("xfermode的DST模式的使用", DstMainActivity.class);
        dataMaps.put("xfermode的其他模式的使用", OtherModeActivity.class);
    }
}
