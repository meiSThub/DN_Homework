package com.mei.arouter.submodule;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mei.arouter.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 在组件化的时候，往往都需要获取组件的一些服务类，用于对外提供一些功能封装
 */
@Route(path = ServiceActivity.PATH)
public class ServiceActivity extends AppCompatActivity {

    public static final String PATH = "/goods/goodsList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }
}