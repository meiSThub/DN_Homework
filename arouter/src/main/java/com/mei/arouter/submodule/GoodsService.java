package com.mei.arouter.submodule;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.launcher.ARouter;

import android.content.Context;

/**
 * @author mxb
 * @date 2022/5/31
 * @desc ARouter 通过IProvider接口，让组件对外提供类功能服务
 * @desired
 */
@Route(path = "/service/goods")
public class GoodsService implements IGoodsService, IProvider {

    private Context mContext;

    @Override
    public void init(Context context) {
        this.mContext = context;
    }

    /**
     * 提供一个跳转到商品列表的方法，这样其它组件只要拿到这个服务对象，就可以跳转到商品列表页面，而不用在写路由跳转了
     */
    @Override
    public void go2GoodsList() {
        ARouter.getInstance().build(ServiceActivity.PATH).navigation();
    }
}
