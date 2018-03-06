package com.mei.design.mode15AbstractFactory.bean;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:实体类，对用数据库中的部门表
 */

public class Department {

    private int id;
    private String deptName;//部门名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
