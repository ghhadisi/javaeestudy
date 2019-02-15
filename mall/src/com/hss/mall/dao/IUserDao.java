package com.hss.mall.dao;

import java.sql.SQLException;

public interface IUserDao {
    public boolean checkUserNameIsExist(String username)throws SQLException;
}
