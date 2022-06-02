package com.mei.arouter.degrade;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mei.arouter.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 全局失败页面
 */
@Route(path = FullFailActivity.PATH)
public class FullFailActivity extends AppCompatActivity {

    public static final String PATH = "/full/fail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_fail);
    }
}