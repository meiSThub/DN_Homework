package com.mei.test.base;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.mei.test.DemoApplication;
import com.mei.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mei on 2017/5/21.
 * Description:
 */
public class BaseListActivity extends ListActivity {

    protected List<String> mTitleList = new ArrayList<>();
    protected List<String> mActionList = new ArrayList<>();
    protected List<Class> mClasses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
//        setData();
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

    protected void setData() {
        DemoApplication application = (DemoApplication) getApplication();
        Map<Class, String> actions = application.getActions();
        Set<Map.Entry<Class, String>> entrySet = actions.entrySet();
        for (Map.Entry<Class, String> entry : entrySet) {
            Class c = entry.getKey();
            String title = entry.getValue();
            mClasses.add(c);
            mTitleList.add(title);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivityByAction(position);
//        startActivityByClass(position);
    }

    private void startActivityByAction(int position) {
        if (position < 0 || position >= mActionList.size()) {
            Toast.makeText(this, "没有配置Action", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction(mActionList.get(position));
        startActivity(intent);
    }

    private void startActivityByClass(int position) {
        if (position < 0 || position >= mClasses.size()) {
            Toast.makeText(this, "没有配置Action", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, mClasses.get(position));
        startActivity(intent);
    }
}
