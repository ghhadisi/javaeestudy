package com.hss.mall.service;

import com.hss.mall.dao.IUserDao;
import com.hss.mall.dao.UserDaoImpl;
import com.hss.mall.domain.User;

import java.sql.SQLException;

public class UserServiceImpl implements IUserService {


    IUserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(User user) throws SQLException{


        return false;
    }

    @Override
    public boolean checkUserNameCanUse(String username) throws SQLException {
        System.out.println(username);
        boolean flag =  userDao.checkUserNameIsExist(username);
        return !flag;
    }
}
