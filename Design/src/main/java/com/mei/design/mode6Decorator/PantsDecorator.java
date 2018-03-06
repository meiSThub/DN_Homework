package com.mei.design.mode6Decorator;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:具体装饰类，人穿什么裤子
 */

public class PantsDecorator extends Decorator {

    private String mPants;

    public PantsDecorator(Person person, String pants) {
        super(person);
        mPants = pants;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("    穿" + mPants);
    }
}
