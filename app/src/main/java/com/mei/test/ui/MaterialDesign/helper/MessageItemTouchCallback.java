package com.mei.test.ui.MaterialDesign.helper;

import android.graphics.Canvas;


import com.mei.test.ui.MaterialDesign.ItemTouchHelpExtend.ItemTouchHelper;
import com.mei.test.ui.MaterialDesign.adapter.MainRecyclerAdapter;

import androidx.recyclerview.widget.RecyclerView;

public class MessageItemTouchCallback extends ItemTouchHelper.Callback {

    private static final String TAG = "ItemTouchCallback";
    private ItemTouchHelperAdapterCallback adapterCallback;

    public MessageItemTouchCallback(ItemTouchHelperAdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
    }

    public MessageItemTouchCallback() {
    }

    /*@Override
    public int getMovementFlags(RecyclerView recyclerView, ViewHolder holder) {
        //callback回调监听哪些动作？---判断方向


        //makeMovementFlags(UP | DOWN, LEFT);
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
//		return 0;
    }

    @Override
    public boolean onMove(RecyclerView arg0, ViewHolder srcHolder, ViewHolder targetHolder) {
        // 监听滑动（水平方向、垂直方向）
        //让数据集合中的两个数据进行位置交换
        //同时还要刷新RecyclerView
//        adapterCallback.onItemMove(srcHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            ViewHolder viewHolder, float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {
        Log.d(TAG, "onChildDraw");
        // 实现一些动画
        //条件：侧滑动作
        if (dY != 0 && dX == 0) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        MainRecyclerAdapter.ItemBaseViewHolder holder = (MainRecyclerAdapter.ItemBaseViewHolder) viewHolder;
        if (viewHolder instanceof MainRecyclerAdapter.ItemSwipeWithActionWidthNoSpringViewHolder) {
            if (dX < -holder.mActionContainer.getWidth()) {
                dX = -holder.mActionContainer.getWidth();
            }
            holder.mViewContent.setTranslationX(dX);
            return;
        }
        if (viewHolder instanceof MainRecyclerAdapter.ItemBaseViewHolder) {
            holder.mViewContent.setTranslationX(dX);
        }
    }

    @Override
    public void onSwiped(ViewHolder holder, int arg1) {
        // 滑动删除的动作的时候回调
        //1.删除数据集合里面的position位置的数据
        //2.刷新adapter
       *//* if (adapterCallback != null) {
            adapterCallback.onItemSwiped(holder.getAdapterPosition());
        }*//*
    }

    //滑动消失的距离，当滑动小于这个值的时候会删除这个item，否则不会视为删除
    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.1f;//指定这个值之后，收拢之前打开的item会变的很苦难
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return 5f;
    }

    //设置手指离开后ViewHolder的动画时间
    @Override
    public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return 100;
    }

    //网格型RecyclerView
    @Override
    public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.9f;
    }

    //返回值决定是否有滑动操作
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
*/

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipeFlag = ItemTouchHelper.LEFT;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        MainRecyclerAdapter adapter = (MainRecyclerAdapter) recyclerView.getAdapter();
        adapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (dY != 0 && dX == 0) {//上下移动
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        //左右侧滑
        MainRecyclerAdapter.ItemBaseViewHolder holder = (MainRecyclerAdapter.ItemBaseViewHolder) viewHolder;
        if (viewHolder instanceof MainRecyclerAdapter.ItemSwipeWithActionWidthNoSpringViewHolder) {
            if (dX < -holder.mActionContainer.getWidth()) {
                dX =- holder.mActionContainer.getWidth();
            }
            holder.mViewContent.setTranslationX(dX);
            return;
        }
        if (viewHolder instanceof MainRecyclerAdapter.ItemBaseViewHolder) {
            holder.mViewContent.setTranslationX(dX);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

}
