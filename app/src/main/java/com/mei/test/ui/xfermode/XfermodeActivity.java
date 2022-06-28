package com.mei.test.ui.xfermode;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mei.test.base.BaseActivity;
import com.mei.test.ui.xfermode.widget.MyView;

/**
 * Created by mei on 2017/6/14.
 * Description:
 */
public class XfermodeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        XfermodeView view = new XfermodeView(this);
        MyView view = new MyView(this);
//        RoundView view=new RoundView(this);
        setContentView(view);
    }

    @Override
    public String getItemTitle() {
        return null;
    }
}
