package ubt.com.test.animation.widget;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by ubt on 2017/12/26.
 *
 * @description:利用Camara实现自定义动画
 */

public class CustomAnimation extends Animation {

    private int mCenterHeight;
    private int mCenterWidth;
    private Camera mCamera;
    private float mRotateY;//Y轴旋转的角度

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //设置默认时长
        setDuration(2000);
        //设置动画结束后保留状态
        setFillAfter(true);
        //设置默认差之器
        setInterpolator(new LinearInterpolator());
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        mCamera.save();
        //使用Camera设置旋转的角度
        mCamera.rotateY(mRotateY * interpolatedTime);
        //将旋转变换作用到Matrix上
        mCamera.getMatrix(matrix);
        mCamera.restore();
        //通过pre方法设置举证作用钱的偏移量来改变旋转中心
        matrix.preTranslate(mCenterWidth, mCenterHeight);//即在旋转之前，先执行平移操作，
        matrix.postTranslate(-mCenterWidth, -mCenterHeight);
    }

    public void setRotateY(float rotateY) {
        mRotateY = rotateY;
    }
}
