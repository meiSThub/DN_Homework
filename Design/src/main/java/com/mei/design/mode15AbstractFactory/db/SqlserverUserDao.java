package com.mei.design.mode15AbstractFactory.db;


import com.mei.design.mode15AbstractFactory.bean.User;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:在Sqlserver数据库中，实现对User表的增加查询操作
 */

public class SqlserverUserDao implements IUserDao {

    @Override
    public void insert(User user) {
        System.out.println("在Sqlserver数据库中，增加用户（User）数据");
    }

    @Override
    public User queryById(int id) {
        System.out.println("在Sqlserver数据库中，根据id查询用户（User）数据");
        return null;
    }
}
