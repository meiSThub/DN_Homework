package com.mei.design.mode14Observer;

/**
 * Created by ubt on 2018/1/15.
 *
 * @description:显示当前天气的公告牌,具体的观察者
 */

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private WeatherData mWeatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
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
        System.out.println("当前温度为：" + mWeatherData.getTemperature() + "℃");
        System.out.println("当前湿度为：" + mWeatherData.getHumidity());
        System.out.println("当前气压为：" + mWeatherData.getPressure());
    }
}
