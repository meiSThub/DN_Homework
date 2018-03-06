package ubt.com.test.systeminfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ubt.com.test.R;

/**
 * PackageManager的用法介绍
 */
public class PackageManagerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ALL_APP = 0;
    public static final int SYSTEM_APP = 1;
    public static final int RD3_APP = 2;//第三方应用
    public static final int SDCARD_APP = 3;

    private ListView mListView;
    private List<ApplicationInfo> mInfoList;
    private List<ApplicationInfo> mDataList = new ArrayList<>();
    private PackageManager pm;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_manager);

        mListView = findViewById(R.id.list_view);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        initData();
    }

    private void initData() {
        mInfoList = new ArrayList<>();
        pm = getPackageManager();
        mInfoList = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        mAdapter.setDataList(mInfoList);
    }

    private List<ApplicationInfo> getData(int flag) {
        switch (flag) {
            case ALL_APP:
                mDataList.clear();
                mDataList.addAll(mInfoList);
                break;
            case SYSTEM_APP:
                mDataList.clear();
                for (ApplicationInfo info : mInfoList) {
                    if ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        mDataList.add(info);
                    }
                }
                break;
            case RD3_APP:
                mDataList.clear();
                for (ApplicationInfo info : mInfoList) {
                    if ((info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        mDataList.add(info);
                    }
                }
                break;
            case SDCARD_APP:
                mDataList.clear();
                for (ApplicationInfo info : mInfoList) {
                    if ((info.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        mDataList.add(info);
                    }
                }
                break;
        }

        return mDataList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_all_app:
                mAdapter.setDataList(mInfoList);
                break;
            case R.id.btn_system_app:
                mAdapter.setDataList(getData(SYSTEM_APP));
                break;
            case R.id.btn_3rd_app:
                mAdapter.setDataList(getData(RD3_APP));
                break;
            case R.id.btn_sdcard_app:
                mAdapter.setDataList(getData(SDCARD_APP));
                break;
        }
    }

    public class MyAdapter extends BaseAdapter {

        public List<ApplicationInfo> mDataList;

        public void setDataList(List<ApplicationInfo> dataList) {
            mDataList = dataList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mDataList == null || position < 0 || position >= mDataList.size() ? null : mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package_manager, parent, false);
                holder = new ViewHolder();
                holder.tvAppName = convertView.findViewById(R.id.tv_app_name);
                holder.tvPackageName = convertView.findViewById(R.id.tv_package_name);
                holder.ivIcon = convertView.findViewById(R.id.img_icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            onBindView(holder, position);
            return convertView;
        }

        public void onBindView(ViewHolder holder, int position) {
            ApplicationInfo info = getItem(position);
            if (info != null) {
                holder.ivIcon.setImageDrawable(info.loadIcon(pm));
                holder.tvAppName.setText(info.loadLabel(pm));
                holder.tvPackageName.setText(info.packageName);
            }
        }
    }

    public class ViewHolder {
        public TextView tvAppName;
        public TextView tvPackageName;
        public ImageView ivIcon;
    }
}
