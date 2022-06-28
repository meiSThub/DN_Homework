package ubt.com.test.animation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ubt.com.test.R;
import ubt.com.test.animation.utils.MyLayoutAnimationHelper;

/**
 * 布局动画：LayoutAnimationControl
 */
public class LayoutAnimationActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new DemoRecyclerViewAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_left) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromLeft());
        } else if (id == R.id.action_right) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromRight());
        } else if (id == R.id.action_bottom) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromBottom());
        } else if (id == R.id.action_top) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromTop(), true);
        } else if (id == R.id.action_scale_enlarge) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetScaleBig());
        } else if (id == R.id.action_scale_narrow) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetScaleNarrow());
        } else if (id == R.id.action_alpha) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetAlpha());
        } else if (id == R.id.action_rotation) {
            playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetRotation());
        }
        return super.onOptionsItemSelected(item);
    }

    public void playLayoutAnimation(Animation animation) {
        playLayoutAnimation(animation, false);
    }

    private void playLayoutAnimation(Animation animation, boolean isReverse) {
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.9f);
        controller.setOrder(isReverse ? LayoutAnimationController.ORDER_REVERSE : LayoutAnimationController.ORDER_NORMAL);
        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }

    public static class DemoRecyclerViewAdapter extends RecyclerView.Adapter<DemoRecyclerViewAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 32;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
