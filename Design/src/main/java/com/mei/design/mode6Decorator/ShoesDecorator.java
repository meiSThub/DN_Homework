package com.mei.design.mode6Decorator;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:具体的装饰类，穿鞋
 */

public class ShoesDecorator extends Decorator {

    private String mShoes;//穿什么鞋子

    public ShoesDecorator(Person person, String shoes) {
        super(person);
        mShoes = shoes;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("    穿" + mShoes);
    }
}
