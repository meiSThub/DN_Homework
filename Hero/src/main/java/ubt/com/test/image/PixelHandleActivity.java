package ubt.com.test.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ubt.com.test.R;
import ubt.com.test.image.helper.ColorPixelsHandle;
import ubt.com.test.image.helper.EmbossPixelsHandle;
import ubt.com.test.image.helper.NegativeColorHandle;
import ubt.com.test.image.helper.OldPixelsHandle;

/**
 * 像素点处理效果
 */
public class PixelHandleActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixel_handle);

        mImageView = findViewById(R.id.img_bg);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mei_nv);
        mImageView.setImageBitmap(mBitmap);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dipian:
                mImageView.setImageBitmap(handleImageNegative(mBitmap, new NegativeColorHandle()));
                break;
            case R.id.btn_old:
                mImageView.setImageBitmap(handleImageNegative(mBitmap, new OldPixelsHandle()));
                break;
            case R.id.btn_fu_diao:
                mImageView.setImageBitmap(handleImageNegative(mBitmap, new EmbossPixelsHandle()));
                break;
        }
    }

    private Bitmap handleImageNegative(Bitmap bm, ColorPixelsHandle pixelsHandle) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        int[] newPx = pixelsHandle.handleColorPixel(oldPx);
        bitmap.setPixels(newPx, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
