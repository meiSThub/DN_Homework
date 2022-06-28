package ubt.com.test.five.x;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.palette.graphics.Palette;
import ubt.com.test.R;


public class PaletteActivity extends AppCompatActivity {

    private Bitmap mBitmap;
    private TextView mTextView;
    private float mValueZ = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_moon);
        //创建Palette对象
        Palette.generateAsync(mBitmap, new Palette.PaletteAsyncListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onGenerated(@NonNull Palette palette) {
                Palette.Swatch vibrant = palette.getMutedSwatch();
                //将颜色设置给相应的组件
                Window window = getWindow();
                window.setStatusBarColor(vibrant.getRgb());
            }
        });

        mTextView = (TextView) findViewById(R.id.text_view);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mTextView.animate().translationZ(mValueZ);
                mValueZ += 100;
            }
        });
    }
}
