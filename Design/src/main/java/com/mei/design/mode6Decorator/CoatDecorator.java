package com.mei.design.mode6Decorator;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:具体的装饰类，穿什么外套
 */

public class CoatDecorator extends Decorator {

    private String mCoat;//穿什么样的外套

    public CoatDecorator(Person person, String coat) {
        super(person);
        mCoat = coat;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("    穿" + mCoat);
    }
}
