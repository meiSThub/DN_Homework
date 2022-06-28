package com.mei.test.ui.MaterialDesign;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mei.test.R;
import com.mei.test.ui.MaterialDesign.ItemTouchHelpExtend.ItemTouchHelper;
import com.mei.test.ui.MaterialDesign.adapter.MainRecyclerAdapter;
import com.mei.test.ui.MaterialDesign.helper.MessageItemTouchCallback;
import com.mei.test.ui.MaterialDesign.utils.TestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemTouchHelper的使用
 */
public class ItemTouchHelperActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_helper);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mAdapter.updateData(createTestDatas());

        MessageItemTouchCallback callback = new MessageItemTouchCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private List<TestModel> createTestDatas() {
        List<TestModel> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestModel testModel = new TestModel(i, ":Item Swipe Action Button Container Width");
            if (i == 1) {
                testModel = new TestModel(i, "Item Swipe with Action container width and no spring");
            }
            if (i == 2) {
                testModel = new TestModel(i, "Item Swipe with RecyclerView Width");
            }
            result.add(testModel);
        }
        return result;
    }
}
