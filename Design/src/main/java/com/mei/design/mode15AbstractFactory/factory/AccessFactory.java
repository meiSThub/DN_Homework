package com.mei.design.mode15AbstractFactory.factory;


import com.mei.design.mode15AbstractFactory.db.AccessDepartmentDao;
import com.mei.design.mode15AbstractFactory.db.AccessUserDao;
import com.mei.design.mode15AbstractFactory.db.IDepartmentDao;
import com.mei.design.mode15AbstractFactory.db.IUserDao;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:创建操作具体数据库的具体表的实例
 */

public class AccessFactory implements IDBFactory {

    /**
     * 创建一个操作Access数据库的用户表（User)的实例对象
     *
     * @return
     */
    @Override
    public IUserDao createUserDao() {
        return new AccessUserDao();
    }

    /**
     * 创建一个操作Access数据库的部门表（Department)的实例对象
     *
     * @return
     */
    @Override
    public IDepartmentDao createDepartment() {
        return new AccessDepartmentDao();
    }
}
