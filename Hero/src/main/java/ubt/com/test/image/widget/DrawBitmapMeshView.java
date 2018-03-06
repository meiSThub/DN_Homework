package ubt.com.test.image.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import ubt.com.test.R;

/**
 * Created by ubt on 2017/12/22.
 *
 * @description:通过drawBitmapMesh方法实现红旗飘动的效果
 */
public class DrawBitmapMeshView extends View {
    private static int HEIGHT = 200;//大于0小于图片宽度都行,大于图片尺寸后，显示效果不好
    private static int WIDTH = 200;
    private static final float A = 50;

    private Paint mPaint;
    private Bitmap mBitmap;
    private float[] mOrig;
    private float[] mVerts;
    private float k = 1;


    public DrawBitmapMeshView(Context context) {
        super(context);
        init();
    }

    public DrawBitmapMeshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawBitmapMeshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mei_nv);

        mOrig = new float[2 * (WIDTH + 1) * (HEIGHT + 1)];//为什么要乘2？因为坐标是以(x,y)形式成对存在的。
        mVerts = new float[2 * (WIDTH + 1) * (HEIGHT + 1)];
//        setLayerType(LAYER_TYPE_SOFTWARE, null);
        initPosition();
    }

    private void initPosition() {
        //计算初始像素位置
        float width = mBitmap.getWidth();
        float height = mBitmap.getHeight();
        int index = 0;
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = height * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = width * x / WIDTH;

                mOrig[index * 2 + 0] = mVerts[index * 2 + 0] = fx;
                //这里人为将坐标+100是为了让图像下移，避免扭曲后被屏幕遮挡
                mOrig[index * 2 + 1] = mVerts[index * 2 + 1] = fy + 100;
                index++;
            }
        }
    }

    private void flagWave() {
        for (int j = 0; j <= HEIGHT; j++) {
            for (int i = 0; i <= WIDTH; i++) {
                mVerts[(j * (WIDTH + 1) + i) * 2 + 0] += 0;
                float offsetY = (float) Math.sin((float) i / WIDTH * 2 * Math.PI + Math.PI * k);
                mVerts[(j * (WIDTH + 1) + i) * 2 + 1] = mOrig[(j * (WIDTH + 1) + i) * 2 + 1] + offsetY * A;
            }
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        k += 0.1;
        flagWave();
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, mVerts, 0, null, 0, null);
//        invalidate();
    }
}
