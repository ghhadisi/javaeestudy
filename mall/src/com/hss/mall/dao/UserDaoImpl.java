package com.hss.mall.dao;

import com.hss.mall.domain.User;
import com.hss.mall.utils.JDBCUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

    @Override
    public User userActive(String code) throws SQLException {

        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return   runner.query("select * from user where code=?", new BeanHandler<User>(User.class),code);
    }

    @Override
    public void updateUser(User user01) throws SQLException {
        Object[] params={user01.getUsername(),user01.getPassword(),user01.getName(),user01.getEmail(),user01.getTelephone(),user01.getBirthday(),user01.getSex(),user01.getState(),user01.getCode(),user01.getUid()};
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        runner.update("update user set username=? ,password=? ,name=? ,email=? ,telephone=? ,birthday=? ,sex=? ,state=? ,code=? WHERE uid=?", params);
    }

    @Override
    public User login(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={user.getUsername(),user.getPassword()};

        return runner.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),params);
    }
}
