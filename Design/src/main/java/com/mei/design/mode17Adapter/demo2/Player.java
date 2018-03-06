package com.mei.design.mode17Adapter.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:球员抽象类
 */

public abstract class Player {

    private String name;

    /**
     * 进攻
     */
    public abstract void attack();

    /**
     * 防守
     */
    public abstract void defense();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
