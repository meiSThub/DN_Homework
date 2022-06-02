package com.mei.arouter;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static com.mei.arouter.SecondActivity.ROUTER_URL;

@Route(path = ROUTER_URL)
public class SecondActivity extends AppCompatActivity {

    public static final String ROUTER_URL = "/user/second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}