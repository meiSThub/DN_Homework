package ubt.com.test.image;

import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import ubt.com.test.R;

/**
 * Android颜色变换矩阵:
 * 通过颜色矩阵修改图片的色调、饱和度、亮度等
 */
public class ColorMatrixActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static int MAX_VALUE = 255;
    private static final int MID_VALUE = 127;
    private ImageView mImageView;
    private SeekBar mSeekBar1, mSeekBar2, mSeekBar3;
    private float mHue;
    private float mLum;
    private float mStauration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        mImageView = findViewById(R.id.img);
        mSeekBar1 = findViewById(R.id.sb_se_diao);
        mSeekBar2 = findViewById(R.id.sb_bao_he_du);
        mSeekBar3 = findViewById(R.id.sb_liang_du);
        mSeekBar1.setOnSeekBarChangeListener(this);
        mSeekBar2.setOnSeekBarChangeListener(this);
        mSeekBar3.setOnSeekBarChangeListener(this);

        mSeekBar1.setMax(MAX_VALUE);
        mSeekBar2.setMax(MAX_VALUE);
        mSeekBar3.setMax(MAX_VALUE);
        mSeekBar1.setProgress(MID_VALUE);
        mSeekBar2.setProgress(MID_VALUE);
        mSeekBar3.setProgress(MID_VALUE);
    }


    public void handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        mImageView.setColorFilter(new ColorMatrixColorFilter(imageMatrix));

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_se_diao:
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180f;
                break;
            case R.id.sb_bao_he_du:
                mStauration = progress * 1.0f / MID_VALUE;
                break;
            case R.id.sb_liang_du:
                mLum = progress * 1.0f / MID_VALUE;
                break;
        }
        handleImageEffect(null, mHue, mStauration, mLum);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
