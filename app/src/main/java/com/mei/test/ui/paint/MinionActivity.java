package com.mei.test.ui.paint;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.paint.widget.MinionView;

/**
 * Created by mei on 2017/5/21.
 * Description:画笔的基本使用，画小黄人
 */
public class MinionActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        com.mei.test.MinionView view = new com.mei.test.MinionView(this);
        MinionView view = new MinionView(this);
        setContentView(view);
    }

    @Override
    public String getItemTitle() {
        return "Paint的基本使用";
    }
}
