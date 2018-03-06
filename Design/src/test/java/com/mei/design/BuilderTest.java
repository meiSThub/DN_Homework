package com.mei.design;

import com.mei.design.mode13Builder.Builder;
import com.mei.design.mode13Builder.Car;
import com.mei.design.mode13Builder.CarBuilder;
import com.mei.design.mode13Builder.CarDirector;

import org.junit.Test;



/**
 * Created by ubt on 2018/1/12.
 *
 * @description:
 */

public class BuilderTest {

    @Test
    public void test(){
        Builder builder = new CarBuilder();
        Car car = CarDirector.assembleProduct(builder);
        System.out.println("car=" + car);
    }
}
