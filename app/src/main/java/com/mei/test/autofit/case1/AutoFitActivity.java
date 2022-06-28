package com.mei.test.autofit.case1;

import android.os.Bundle;
import android.view.View;

import com.mei.test.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 屏幕适配方式一
 */
public class AutoFitActivity extends AppCompatActivity {

    private View mTxt1, mTxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_fit);

        mTxt1 = findViewById(R.id.txt1);
        mTxt2 = findViewById(R.id.txt2);

        ViewCalculateUtils.setViewLinearLayoutParams(mTxt1, 1040, 80, 10, 0, 20, 20);
        ViewCalculateUtils.setViewLinearLayoutParams(mTxt2, 400, 400, 0, 50, 0, 0);
    }
}
