package com.mei.design;

import com.mei.design.mode14Observer.CurrentConditionsDisplay;
import com.mei.design.mode14Observer.ForecastDisplay;
import com.mei.design.mode14Observer.WeatherData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ubt on 2018/1/15.
 *
 * @description:观察者模式测试用例
 */

public class ObserverTest {

    @Test
    public void test() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        List<Float> forecastTemperatures = new ArrayList<>();
        forecastTemperatures.add(22f);
        forecastTemperatures.add(-1f);
        forecastTemperatures.add(9f);
        forecastTemperatures.add(23f);
        forecastTemperatures.add(27f);
        forecastTemperatures.add(30f);
        forecastTemperatures.add(10f);
        weatherData.setMeasurements(22f, 0.8f, 1.2f, forecastTemperatures);
    }
}
