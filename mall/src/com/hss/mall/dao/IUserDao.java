package com.hss.mall.dao;

import com.hss.mall.domain.User;

import java.sql.SQLException;

public interface IUserDao {
    public boolean checkUserNameIsExist(String username)throws SQLException;


    public void register(User user)throws SQLException;
}
