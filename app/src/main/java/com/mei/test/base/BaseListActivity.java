package com.mei.test.base;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.mei.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mei on 2017/5/21.
 * Description:
 */
public class BaseListActivity extends ListActivity {

    protected List<String> mTitleList = new ArrayList<>();
    protected List<String> mActionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    protected void getData() {
        String array[] = getResources().getStringArray(R.array.ListItem);
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    String item[] = array[i].split(";");
                    if (item != null && item.length >= 2) {
                        mTitleList.add(item[0]);
                        mActionList.add(item[1]);
                    }
                }
            }
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position < 0 || position >= mActionList.size()) {
            Toast.makeText(this, "没有配置Action", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction(mActionList.get(position));
        startActivity(intent);
    }
}
