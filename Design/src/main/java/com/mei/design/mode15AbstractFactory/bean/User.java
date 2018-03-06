package com.mei.design.mode15AbstractFactory.bean;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:实体类，对应数据库中的用户表
 */

public class User {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
