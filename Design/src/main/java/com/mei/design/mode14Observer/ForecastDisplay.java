package com.mei.design.mode14Observer;

import java.util.List;

/**
 * Created by ubt on 2018/1/15.
 *
 * @description:显示未来几天天气的公告牌,具体观察者实现
 */

public class ForecastDisplay implements Observer, DisplayElement {

    private WeatherData mWeatherData;

    public ForecastDisplay(WeatherData weatherData) {
        mWeatherData = weatherData;
        if (mWeatherData != null) {
            mWeatherData.registerObserver(this);
        }
    }

    @Override
    public void update() {
        display();
    }

    @Override
    public void display() {
        System.out.println("未来几天的气温");
        List<Float> list = mWeatherData.getForecastTemperatures();
        if (list == null) return;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            System.out.println("第" + i + "天:" + list.get(i) + "℃");
        }
    }
}
