package com.mei.design.mode15AbstractFactory.factory;


import com.mei.design.mode15AbstractFactory.db.IDepartmentDao;
import com.mei.design.mode15AbstractFactory.db.IUserDao;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:定义数据库操作的抽象工厂，决定使用哪种数据库
 */

public interface IDBFactory {

    /**
     * 创建操作用户表的数据库实例
     *
     * @return
     */
    IUserDao createUserDao();

    /**
     * 创建操作部门表（Department）的数据库实例
     *
     * @return
     */
    IDepartmentDao createDepartment();

}
