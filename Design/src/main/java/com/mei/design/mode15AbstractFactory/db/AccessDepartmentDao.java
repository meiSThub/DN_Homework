package com.mei.design.mode15AbstractFactory.db;


import com.mei.design.mode15AbstractFactory.bean.Department;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:在Access数据库中，实现对Department表的插入查询操作
 */

public class AccessDepartmentDao implements IDepartmentDao {
    @Override
    public void insert(Department department) {
        System.out.println("在Access数据库中，增加部门（Department）数据");
    }

    @Override
    public Department query(int id) {
        System.out.println("在Access数据库中，根据id查询部门（Department）数据");
        return null;
    }
}
