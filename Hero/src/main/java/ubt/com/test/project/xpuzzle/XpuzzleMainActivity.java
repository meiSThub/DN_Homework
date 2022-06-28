package ubt.com.test.project.xpuzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ubt.com.test.R;
import ubt.com.test.project.utils.ScreenUtils;
import ubt.com.test.project.xpuzzle.bean.PuzzleInfo;

/**
 * 拼图游戏实现
 */
public class XpuzzleMainActivity extends AppCompatActivity implements View.OnClickListener {


    private List<PuzzleInfo> mDataList;
    private TextView mBtnCheckLevel;
    private RecyclerView mRecyclerView;
    private PopupWindow mPopupWindow;
    private ListView mListView;
    private MyAdapter mAdapter;
    private int[] mResPicId = new int[]{// 初始化Bitmap数据
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic10, R.drawable.pic11, R.drawable.pic12,
            R.drawable.pic13, R.drawable.pic14,
            R.drawable.pic15, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xpuzzle_main);

        mBtnCheckLevel = findViewById(R.id.tv_check_level);
        mBtnCheckLevel.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        initData();
    }


    public void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < mResPicId.length; i++) {
            PuzzleInfo info = new PuzzleInfo(i, mResPicId[i], null);
            mDataList.add(info);
        }
        mAdapter.setDataList(mDataList);
    }

    @Override
    public void onClick(View v) {
        ensurePopumWindow();
        mPopupWindow.showAsDropDown(mBtnCheckLevel);
    }

    private void ensurePopumWindow() {
        if (mPopupWindow == null) {
            View root = LayoutInflater.from(this).inflate(R.layout.layout_check_level, null);
            mListView = root.findViewById(R.id.list_view);
            mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                    new String[]{"2*2", "3*3", "4*4"}));

            mPopupWindow = new PopupWindow(root, (int) ScreenUtils.getScreenWidth(getApplicationContext()), 600);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(true);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mPopupWindow.dismiss();
                    RecyclerView.LayoutManager manager = null;
                    switch (position) {
                        case 0:
                            manager = new GridLayoutManager(XpuzzleMainActivity.this, 2);
                            mBtnCheckLevel.setText("选择难度：2*2");
                            break;
                        case 1:
                            manager = new GridLayoutManager(XpuzzleMainActivity.this, 3);
                            mBtnCheckLevel.setText("选择难度：3*3");
                            break;
                        case 2:
                            manager = new GridLayoutManager(XpuzzleMainActivity.this, 4);
                            mBtnCheckLevel.setText("选择难度：4*4");
                            break;
                    }
                    mRecyclerView.setLayoutManager(manager);
                }
            });
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<PuzzleInfo> mDataList;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puzzle, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            PuzzleInfo info = getItem(position);
            if (info != null) {
                holder.imageView.setBackgroundResource(info.bitmapId);
            }

        }

        @Override
        public int getItemCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        public PuzzleInfo getItem(int position) {
            return mDataList == null || position < 0 || position >= mDataList.size() ? null : mDataList.get(position);
        }

        public void setDataList(List<PuzzleInfo> dataList) {
            mDataList = dataList;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView;
            }
        }
    }
}
