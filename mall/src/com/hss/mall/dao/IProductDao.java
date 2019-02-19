package com.hss.mall.dao;

import com.hss.mall.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    List<Product> findNewProducts()throws SQLException;

    List<Product> findHotProducts()throws SQLException ;

    public Product findProductByPid(String pid) throws SQLException ;


}
