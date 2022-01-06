package com.mei.design.mode7Proxy.star;

/**
 * Created by ubt on 2018/1/9.
 *
 * @description:抽象角色：提供代理角色和真实角色对外提供的公共方法
 */

public interface IStar {

    /**
     * 接受节目邀请
     */
    void accept(String invitation);
}
