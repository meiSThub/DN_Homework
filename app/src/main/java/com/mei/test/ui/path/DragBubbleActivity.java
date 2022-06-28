package com.mei.test.ui.path;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;
import com.mei.test.ui.path.widget.DragBubbleView;

/**
 * Created by mei on 2017/7/18.
 * Description:qq消息气泡的实现
 */
public class DragBubbleActivity extends BaseActivity {

    private DragBubbleView mBubbleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_bubble);
        mBubbleView = (DragBubbleView) findViewById(R.id.drag_buddle_view);
    }

    public void onClick(View v) {
        mBubbleView.reset();
    }
}
