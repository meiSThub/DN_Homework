package com.mei.test.ui.palatte;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import android.view.View;

import com.mei.test.R;
import com.mei.test.base.BaseActivity;
import com.mei.test.utils.LogUtils;

/**
 * Created by mei on 2017/7/9.
 * Description:
 */
public class PaletteActivity extends BaseActivity {

    private View mViewItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_main);

        mViewItem = findViewById(R.id.bg);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_two);
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getDarkVibrantSwatch();
                LogUtils.i("plum", "swatch=" + swatch);
                if (swatch != null) {
                    mViewItem.setBackgroundColor(swatch.getRgb());
                } else {
                    for (Palette.Swatch s : palette.getSwatches()) {
                        if (s != null) {
                            mViewItem.setBackgroundColor(s.getRgb());
                        }
                    }
                }
            }
        });

    }

    private int changeColor(int argb) {

        return argb;
    }
}
