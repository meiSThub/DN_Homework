package com.mei.test.ui.paint;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;

/**
 * Created by mei on 2017/6/11.
 * Description:水波纹按钮
 */
public class RippleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);
    }

    @Override
    public String getItemTitle() {
        return "水波纹按钮";
    }


}
