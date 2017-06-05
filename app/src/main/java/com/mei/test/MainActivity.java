package com.mei.test;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.mei.test.base.BaseListActivity;


public class MainActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<>(this, R.layout.item_list, R.id.txt_title, mTitleList));
    }


}
