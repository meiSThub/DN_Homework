package com.mei.arouter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mei.arouter.submodule.GoodsService;
import com.mei.arouter.submodule.IGoodsService;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Autowired
    public GoodsService mGoodsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_main);
    }

    public void go2SecondPage(View view) {
        ARouter.getInstance().build(SecondActivity.ROUTER_URL).navigation();
    }

    public void jumpWidthParams(View view) {
        ARouter.getInstance().build(JumpWithParamsActivity.ROUTER_URL)
                .withString("pageName", "ARouter跳转带参数，我就是参数")
                .navigation();
    }

    public void callback(View view) {
        ARouter.getInstance().build(CallbackActivity.ROUTER_URL)
                .navigation(this, 100);
    }

    public void service(View view) {
        // ARouter.getInstance().navigation(GoodsService.class).go2GoodsList();
        mGoodsService.go2GoodsList();
        // ((IGoodsService) ARouter.getInstance().build("/service/goods").navigation()).go2GoodsList();
    }

    public void degradeService(View view) {
        ARouter.getInstance().build("/user/jumpWidthParams-error")
                .navigation();
    }
}