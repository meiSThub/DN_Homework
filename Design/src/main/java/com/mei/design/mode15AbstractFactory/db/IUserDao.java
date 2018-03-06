package com.mei.design.mode15AbstractFactory.db;


import com.mei.design.mode15AbstractFactory.bean.User;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:定义一系列操作用户表的接口
 */

public interface IUserDao {
    /**
     * 向用户表中插入一条数据
     *
     * @param user
     */
    void insert(User user);

    /**
     * 根据用户id，查询用户信息
     *
     * @param id
     */
    User queryById(int id);
}
