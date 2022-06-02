package com.mei.arouter.degrade;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;

import android.content.Context;
import android.util.Log;

/**
 * @author mxb
 * @date 2022/5/31
 * @desc 当路由找不到或者不合法当时候的处理逻辑就是降级策略，实现DegradeService接口，并加上一个Path内容任意的注解即可
 * @desired
 */
@Route(path = "/degrade/DegradeServiceImpl")
public class DegradeServiceImpl implements DegradeService {

    private static final String TAG = "DegradeServiceImpl";

    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.i(TAG, "onLost: 降级策略，进入全局失败页面");
        ARouter.getInstance().build(FullFailActivity.PATH).navigation();
    }

    @Override
    public void init(Context context) {

    }
}
