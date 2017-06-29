package com.mei.test.ui.canvas;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;
import com.mei.test.ui.canvas.widget.GallaryHorizonalScrollView;
import com.mei.test.ui.canvas.widget.RevealDrawable2;

/**
 * Created by mei on 2017/6/29.
 * Description:
 */
public class RevealActivity extends BaseActivity {

    private ImageView iv;
    private int[] mImgIds = new int[]{ //7个
            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline,

            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline
    };
    private int[] mImgIds_active = new int[]{
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active,
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active
    };

    private ImageView mImageView;
    private int level = 10000;
    private Drawable[] revealDrawables;
    private GallaryHorizonalScrollView hzv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reveal);

        mImageView = (ImageView) findViewById(R.id.img_icon);

        final RevealDrawable2 drawable = new RevealDrawable2(getResources().getDrawable(mImgIds[0]), getResources().getDrawable(mImgIds_active[0]));
        mImageView.setImageDrawable(drawable);
        mImageView.setImageLevel(5000);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level -= 500;
                drawable.setLevel(level);
            }
        });

        initData();
        initView();
    }

    private void initData() {
        revealDrawables = new Drawable[mImgIds.length];
    }

    private void initView() {
        for (int i = 0; i < mImgIds.length; i++) {
            RevealDrawable2 rd = new RevealDrawable2(
                    getResources().getDrawable(mImgIds[i]),
                    getResources().getDrawable(mImgIds_active[i]));
            revealDrawables[i] = rd;
        }
        hzv = (GallaryHorizonalScrollView) findViewById(R.id.hsv_root);
        hzv.addImageViews(revealDrawables);
    }
}
