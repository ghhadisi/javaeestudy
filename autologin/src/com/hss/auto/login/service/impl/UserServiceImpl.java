package com.hss.auto.login.service.impl;

import com.hss.auto.login.bean.UserBean;
import com.hss.auto.login.dao.UserDao;
import com.hss.auto.login.dao.impl.UserDaoImpl;
import com.hss.auto.login.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public UserBean login(UserBean user) throws SQLException {
        UserDao userDao= new UserDaoImpl();
        return userDao.login(user);
    }
}
