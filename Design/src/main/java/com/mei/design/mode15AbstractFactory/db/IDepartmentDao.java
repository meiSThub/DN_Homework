package com.mei.design.mode15AbstractFactory.db;


import com.mei.design.mode15AbstractFactory.bean.Department;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:定义一系列操作数据库部门表的接口
 */

public interface IDepartmentDao {

    /**
     * //向部门表中插入一条数据
     *
     * @param department
     */
    void insert(Department department);

    /**
     * 根据部门id，查询部门信息
     *
     * @param id
     * @return
     */
    Department query(int id);

}
