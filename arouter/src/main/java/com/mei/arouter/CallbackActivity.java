package com.mei.arouter;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static com.mei.arouter.CallbackActivity.ROUTER_URL;

@Route(path = ROUTER_URL)
public class CallbackActivity extends AppCompatActivity {

    public static final String ROUTER_URL = "/user/callback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);
    }
}