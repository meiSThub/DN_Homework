package ubt.com.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ubt.com.test.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private List<String> mDataList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mListView = (ListView) findViewById(R.id.list_view);
//        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                mDataList);
//        mListView.setAdapter(mAdapter);
//
//        mListView.setEmptyView(findViewById(R.id.tv_empty));//当数据为空的时候，显示的提示

    }


    @Override
    public void onClick(View v) {
        for (int i = 0; i < 50; i++) {
            mDataList.add("标题：" + (i + 1));
        }
        mAdapter.notifyDataSetChanged();
    }
}
