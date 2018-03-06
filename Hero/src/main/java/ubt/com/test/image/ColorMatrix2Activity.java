package ubt.com.test.image;

import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import ubt.com.test.R;

/**
 * Android颜色变换矩阵:
 * Android颜色矩阵——ColorMatrix
 */
public class ColorMatrix2Activity extends AppCompatActivity {

    private ImageView mImageView;
    private GridLayout mGridLayout;
    private EditText mEditTexts[] = new EditText[20];
    private int mEditHeight;
    private int mEditWidth;
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix2);
        mImageView = findViewById(R.id.img_bg);
        mGridLayout = findViewById(R.id.grid_layout);

        mGridLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEditWidth = mGridLayout.getMeasuredWidth() / 4;
                mEditHeight = mGridLayout.getMeasuredHeight() / 4;
                createEditTexts();

            }
        }, 100);
    }

    private void createEditTexts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            mEditTexts[i] = editText;
            mGridLayout.addView(editText, mEditWidth, mEditHeight);
        }
        initMatrix();
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEditTexts[i].setText(String.valueOf(1));
            } else {
                mEditTexts[i].setText(String.valueOf(0));
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change:
                getMatrix();
                mImageView.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
                break;
            case R.id.btn_reset:
                initMatrix();
                getMatrix();
                mImageView.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
                break;
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf((mEditTexts[i].getText().toString()));
        }
    }
}
