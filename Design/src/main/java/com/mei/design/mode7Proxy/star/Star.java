package com.mei.design.mode7Proxy.star;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:真实的被代理类，即明星
 */

public class Star implements IStar {

    @Override
    public void accept(String invitation) {
        System.out.println("Star接受节目邀请：" + invitation);
    }
}
