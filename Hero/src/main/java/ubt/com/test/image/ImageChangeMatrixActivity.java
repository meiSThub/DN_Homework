package ubt.com.test.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import ubt.com.test.R;

/**
 * <p> 图形的特效处理方式:
 * (1)使用图形变换矩阵——看本例
 * (2)使用drawBitmapMesh()方法——看DrawBitmapMeshActivity
 * </p>
 * <p>注意与Android颜色变换矩阵的区别，颜色矩阵是对图形色彩的处理_</p>
 *
 * @Description:图形的特效处理方式一：使用图形变换矩阵，Android图形变换矩阵,是对图形的处理
 */
public class ImageChangeMatrixActivity extends AppCompatActivity {

    private ImageView mImageView;
    private GridLayout mGridLayout;
    private EditText mEditTexts[] = new EditText[9];
    private int mEditHeight;
    private int mEditWidth;
    private float[] mMatrix = new float[9];
    private Bitmap mBitmap;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_change_matrix);
        mImageView = findViewById(R.id.img_bg);
        mGridLayout = findViewById(R.id.grid_layout);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mei_nv);

        mGridLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEditWidth = mGridLayout.getMeasuredWidth() / 3;
                mEditHeight = mGridLayout.getMeasuredHeight() / 3;
                createEditTexts();

            }
        }, 100);
    }

    private void createEditTexts() {
        for (int i = 0; i < 9; i++) {
            EditText editText = new EditText(this);
            mEditTexts[i] = editText;
            mGridLayout.addView(editText, mEditWidth, mEditHeight);
        }
        initMatrix();
    }

    private void initMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
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
                mImageView.setImageBitmap(getBitmap(mBitmap));
                break;
            case R.id.btn_reset:
                initMatrix();
                getMatrix();
                mImageView.setImageBitmap(getBitmap(mBitmap));
                break;
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 9; i++) {
            mMatrix[i] = Float.valueOf((mEditTexts[i].getText().toString()));
        }
    }


    private Bitmap getBitmap(Bitmap bp) {
        int width = bp.getWidth();
        int height = bp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Matrix matrix = new Matrix();
        matrix.setValues(mMatrix);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bp, matrix, mPaint);
        return bitmap;
    }
}
