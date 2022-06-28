package com.mei.test.ui.svg.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import androidx.core.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.mei.test.R;
import com.mei.test.ui.svg.bean.CityInfo;
import com.mei.test.utils.LogUtils;
import com.mei.test.utils.PathParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by mei on 2017/6/1.
 * Description:
 */
public class TaiWanMapView extends View {

    private List<CityInfo> mList;
    private CityInfo mSelectCity;
    private Paint mPaint;
    private GestureDetectorCompat mGestureDetector;

    public TaiWanMapView(Context context) {
        this(context, null);
    }

    public TaiWanMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mList = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mThread.start();
        mGestureDetector = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                for (int i = 0; i < mList.size(); i++) {
                    if (isTouch(mList.get(i), e.getX(), e.getY())) {
                        mSelectCity = mList.get(i);
                        postInvalidate();
                        break;
                    }
                }
                return true;
            }
        });
    }

    private Thread mThread = new Thread() {
        @Override
        public void run() {
            super.run();
            try {
                InputStream inputStream = getContext().getResources().openRawResource(R.raw.taiwan);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(inputStream);
                Element rootElement = document.getDocumentElement();
                NodeList items = rootElement.getElementsByTagName("path");
                for (int i = 0; i < items.getLength(); i++) {
                    Element element = (Element) items.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    LogUtils.i("pathData=" + pathData);
                    Path path = PathParser.createPathFromPathData(pathData);
                    CityInfo info = new CityInfo(path);
                    String fillColor = element.getAttribute("android:fillColor");
                    String strokeColor = element.getAttribute("android:strokeColor");
                    String strokeWidth = element.getAttribute("android:strokeWidth");
                    info.setFillColor(fillColor);
                    info.setStrokeColor(strokeColor);
                    info.setStrokeWidth(strokeWidth);
                    mList.add(info);
                }
                mHandler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mList.size(); i++) {
            CityInfo info = mList.get(i);
            drawCity(canvas, info, false);
        }
        if (mSelectCity != null) {
            drawCity(canvas, mSelectCity, true);
        }
    }

    private void drawCity(Canvas canvas, CityInfo info, boolean isSelect) {
        if (isSelect) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(0xFFD77C7C);
            mPaint.setStrokeWidth(info.getStrokeWidth() * 8);
            mPaint.setShadowLayer(8, 0, 0, 0xFFE60C0C);
            canvas.drawPath(info.getPath(), mPaint);

            mPaint.clearShadowLayer();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(0xFFE60C22);
            canvas.drawPath(info.getPath(), mPaint);
        } else {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(info.getFillColor());
            canvas.drawPath(info.getPath(), mPaint);

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(info.getStrokeColor());
            mPaint.setStrokeWidth(info.getStrokeWidth());
            canvas.drawPath(info.getPath(), mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    /**
     * 根据点击的位置，判断选中了那个城市（重点）
     *
     * @param info
     * @param x
     * @param y
     * @return
     */
    private boolean isTouch(CityInfo info, float x, float y) {
        boolean isTouch = false;
        Path path = info.getPath();
        if (path != null) {
            RectF rectF = new RectF();
            path.computeBounds(rectF, true);//测出包裹该区域的最小矩形
            Region region = new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            region.setPath(path, region);//
            isTouch = region.contains((int) x, (int) y);//判断点击的位置是否在该区域内
        }
        return isTouch;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            postInvalidate();//请求重绘
        }
    };
}
