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
    public void accept(String invitation) {
        if (invitation.equals("sing")) {
            System.out.println("StarProxy 接收邀请：" + invitation);
            mStar.accept(invitation);
        } else {
            System.out.println("StarProxy 拒绝邀请：" + invitation);
        }
    }
}
