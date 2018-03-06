package com.mei.design.mode15AbstractFactory.factory;


import com.mei.design.mode15AbstractFactory.db.IDepartmentDao;
import com.mei.design.mode15AbstractFactory.db.IUserDao;
import com.mei.design.mode15AbstractFactory.db.SqlserverDepartmentDao;
import com.mei.design.mode15AbstractFactory.db.SqlserverUserDao;

/**
 * Created by ubt on 2018/1/16.
 *
 * @description:创建操作具体数据库的具体表的实例
 */

public class SqlserverFactory implements IDBFactory {

    /**
     * 创建一个操作SqlServer数据库用户表（User）的实例对象
     *
     * @return
     */
    @Override
    public IUserDao createUserDao() {
        return new SqlserverUserDao();
    }

    /**
     * 创建一个操作Sqlserver数据库部门表(Department)的实例对象
     *
     * @return
     */
    @Override
    public IDepartmentDao createDepartment() {
        return new SqlserverDepartmentDao();
    }
}
