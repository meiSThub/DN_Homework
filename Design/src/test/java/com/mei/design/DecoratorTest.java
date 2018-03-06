package com.mei.design;

import com.mei.design.mode6Decorator.CoatDecorator;
import com.mei.design.mode6Decorator.Man;
import com.mei.design.mode6Decorator.PantsDecorator;
import com.mei.design.mode6Decorator.Person;
import com.mei.design.mode6Decorator.ShoesDecorator;

import org.junit.Test;


/**
 * Created by ubt on 2018/1/9.
 *
 * @description:装饰模式的测试用例
 */

public class DecoratorTest {

    @Test
    public void test() {
        Person man = new Man();
        man.show();

        man = new CoatDecorator(man, "夹克");
        man = new PantsDecorator(man, "牛仔裤");
        man = new ShoesDecorator(man, "球鞋");
        man.show();
    }
}
