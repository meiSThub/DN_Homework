package ubt.com.test.image.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by ubt on 2017/12/22.
 * PathEffect的使用
 *
 * @description:
 */

public class PathEffectView extends View {

    private Path mPath;
    private PathEffect mPathEffects[];
    private Paint mPaint;

    public PathEffectView(Context context) {
        super(context);
        init();
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);

        mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 0; i < 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }

        mPathEffects = new PathEffect[6];
        mPathEffects[0] = null;
        mPathEffects[1] = new CornerPathEffect(30);
        mPathEffects[2] = new DiscretePathEffect(3.0f, 5.0f);
        mPathEffects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mPathEffects[4] = new PathDashPathEffect(path, 12, 0, PathDashPathEffect.Style.ROTATE);
        mPathEffects[5] = new ComposePathEffect(mPathEffects[3], mPathEffects[1]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mPathEffects.length; i++) {
            canvas.translate(0, 200);
            mPaint.setPathEffect(mPathEffects[i]);
            canvas.drawPath(mPath, mPaint);
        }
    }
}

