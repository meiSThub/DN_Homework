package com.mei.design.mode17Adapter.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:外籍球员，中国球员,只能听懂中文，听不懂英文
 */

public class ForeignPlayer {

    private String name;

    public void 进攻() {
        System.out.println("外籍球员：" + name + "进攻");
    }

    public void 防守() {
        System.out.println("外籍球员：" + name + "防守");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
