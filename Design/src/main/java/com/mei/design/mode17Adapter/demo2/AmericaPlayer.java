package com.mei.design.mode17Adapter.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:美国球员，可以听懂英语
 */

public class AmericaPlayer extends Player {
    @Override
    public void attack() {
        System.out.println(getName() + "进攻");
    }

    @Override
    public void defense() {
        System.out.println(getName() + "防守");
    }
}
