package com.mei.design.mode7Proxy.star;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:明星代理类，相当于经纪人
 */

public class StarProxy implements IStar {

    private IStar mStar;

    public StarProxy(IStar star) {
        mStar = star;
    }

    @Override
    public void sing() {
        mStar.sing();
    }

    @Override
    public void accept() {
        System.out.println("StarProxy 代理接受节目邀请");
        mStar.accept();
    }
}
