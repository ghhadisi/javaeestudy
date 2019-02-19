package com.hss.mall.service;

import com.hss.mall.dao.IProductDao;
import com.hss.mall.dao.ProductDaoImpl;
import com.hss.mall.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    IProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> findNewProducts() throws SQLException {
        return productDao.findNewProducts();
    }

    @Override
    public List<Product> findHotProducts() throws SQLException {
        return productDao.findHotProducts();
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        return productDao.findProductByPid(pid);
    }
}
