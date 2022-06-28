package ubt.com.test.five.x;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import ubt.com.test.R;

public class ToolBarDrawerLayoutActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolBar;
    private ActionBarDrawerToggle mDrawerToggle;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tool_bar_drawer_layout);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);


        Drawable drawable = getDrawable(R.mipmap.ic_launcher);
        mToolBar.setLogo(drawable);
        mToolBar.setTitle("主标题");
        mToolBar.setSubtitle("副标题");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ToolBarDrawerLayoutActivity.this,"点击有效",Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolBar, R.string.app_menu, R.string.app_menu_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

/**
 *一、ToolBar的使用：
 *  1、标题的文字需在setSupportActionBar之前，不然会无效
 *  2、
 */
