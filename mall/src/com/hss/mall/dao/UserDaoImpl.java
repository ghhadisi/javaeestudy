package com.hss.mall.dao;

import com.hss.mall.domain.User;
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

    @Override
    public void register(User user) throws SQLException {
        String sql="INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,params);
    }
}
