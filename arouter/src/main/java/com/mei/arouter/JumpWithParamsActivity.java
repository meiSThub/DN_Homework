package com.mei.arouter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static com.mei.arouter.JumpWithParamsActivity.ROUTER_URL;

@Route(path = ROUTER_URL)
public class JumpWithParamsActivity extends AppCompatActivity {

    public static final String ROUTER_URL = "/user/jumpWidthParams";

    @Autowired(name = "pageName")
    public String pageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 解析参数，并赋值，这里传递的是pageName，会自动解析pageName字段，并为pageName属性赋值
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_jump_with_params);
        ((TextView) findViewById(R.id.tvParams)).setText(pageName);
    }
}