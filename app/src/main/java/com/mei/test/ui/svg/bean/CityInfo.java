package com.mei.test.ui.svg.bean;

import android.graphics.Color;
import android.graphics.Path;
import android.text.TextUtils;

/**
 * Created by mei on 2017/6/1.
 * Description:
 */
public class CityInfo {

    private Path mPath;
    private String fillColor;
    private String strokeColor;
    private float strokeWidth;


    public CityInfo(Path path) {
        mPath = path;
    }

    public Path getPath() {
        return mPath;
    }

    public void setPath(Path path) {
        mPath = path;
    }

    public int getFillColor() {
        return Color.parseColor(fillColor);
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public int getStrokeColor() {
        return Color.parseColor(strokeColor);
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setStrokeWidth(String strokeWidth) {
        float width = 0;
        if (!TextUtils.isEmpty(strokeWidth)) {
            width = Float.parseFloat(strokeWidth);
        }
        setStrokeWidth(width);
    }
}
