package com.mei.design;

import com.mei.design.mode12Facade.Facade;

import org.junit.Test;


/**
 * Created by ubt on 2018/1/12.
 *
 * @description:外观模式测试用例
 */

public class FacadeTest {

    @Test
    public void test() {
        Facade facade = new Facade();
        facade.invokeChildSystem1();
        facade.invokeChildSystem2();
        facade.invokeChildSystem3();
    }
}
