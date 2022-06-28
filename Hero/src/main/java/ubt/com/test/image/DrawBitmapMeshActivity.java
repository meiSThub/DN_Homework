package ubt.com.test.image;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import ubt.com.test.R;

/**
 * <p>
 * 图形的特效处理方式:
 * (1)使用图形变换矩阵——看ImageChangeMatrixActivity
 * (2)使用drawBitmapMesh()方法——看本例
 * </p>
 *
 * @Description:图形的特效处理方式二：使用drawBitmapMesh()方法来处理
 */
public class DrawBitmapMeshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bitmap_mesh);
    }
}
