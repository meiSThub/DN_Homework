package com.mei.test.ui.flowlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;

/**
 * Created by mei on 2017/9/23.
 * Description:
 */

public class FlowLayoutActivity extends BaseActivity {

    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        mFlowLayout = (FlowLayout) findViewById(R.id.fl_root);
        mFlowLayout.setOnItemClickListener(new FlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int index) {
                Toast.makeText(FlowLayoutActivity.this, "点击有效", Toast.LENGTH_LONG).show();
            }
        });
    }
}
