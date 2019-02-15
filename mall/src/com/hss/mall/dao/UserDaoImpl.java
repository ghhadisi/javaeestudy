package com.hss.mall.dao;

import com.hss.mall.utils.JDBCUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.SQLException;

public class UserDaoImpl implements IUserDao {
    @Override
    public boolean checkUserNameIsExist(String username) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Long size = (Long) runner.query("select count(*) from user where username = ?",new ScalarHandler(),username);
        return size >0;
//          return false;
    }
}
