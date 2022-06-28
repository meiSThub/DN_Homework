package com.mei.test.ui.paint;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.paint.widget.InvertedImageView;

/**
 * Created by mei on 2017/6/13.
 * Description:倒影图片
 */
public class InvertedImageActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InvertedImageView view = new InvertedImageView(this);
        setContentView(view);
    }

    @Override
    public String getItemTitle() {
        return null;
    }
}
