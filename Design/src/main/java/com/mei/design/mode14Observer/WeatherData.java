package com.mei.design.mode14Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubt on 2018/1/15.
 *
 * @description:天气数据，实现了被观察者接口,使之成为被观察者
 */

public class WeatherData implements Subject {

    private List<Observer> mObserverList = new ArrayList<>();

    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压
    private List<Float> forecastTemperatures;//未来几天的温度



    @Override
    public void registerObserver(Observer observer) {
        if (observer != null) {
            mObserverList.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer != null) {
            mObserverList.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : mObserverList) {
            observer.update();
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity,
                                float pressure, List<Float> forecastTemperatures) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.forecastTemperatures = forecastTemperatures;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public List<Float> getForecastTemperatures() {
        return forecastTemperatures;
    }
}
