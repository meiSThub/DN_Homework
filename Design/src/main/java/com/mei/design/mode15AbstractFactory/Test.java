package com.mei.design.mode15AbstractFactory;


import com.mei.design.mode15AbstractFactory.bean.Department;
import com.mei.design.mode15AbstractFactory.bean.User;
import com.mei.design.mode15AbstractFactory.db.IDepartmentDao;
import com.mei.design.mode15AbstractFactory.db.IUserDao;
import com.mei.design.mode15AbstractFactory.factory.AccessFactory;
import com.mei.design.mode15AbstractFactory.factory.IDBFactory;
import com.mei.design.mode15AbstractFactory.factory.SqlserverFactory;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:抽象工厂模式测试用例
 */

public class Test {

    public static void main(String[] args) {
        System.out.println("访问Sqlserver数据库的用户表和部门表");
        IDBFactory sqlserverDb = new SqlserverFactory();
        IUserDao userDao = sqlserverDb.createUserDao();
        userDao.insert(new User());
        userDao.queryById(1);
        IDepartmentDao departmentDao = sqlserverDb.createDepartment();
        departmentDao.insert(new Department());
        departmentDao.query(1);

        System.out.println("访问Sqlserver数据库的用户表和部门表");

        IDBFactory accessFactory = new AccessFactory();
        IUserDao accessUserDao = accessFactory.createUserDao();
        accessUserDao.insert(new User());
        accessUserDao.queryById(1);
        IDepartmentDao accessDeptDao = accessFactory.createDepartment();
        accessDeptDao.insert(new Department());
        accessDeptDao.query(1);

    }

}
