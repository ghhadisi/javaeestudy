package com.hss.mall.dao;

import com.hss.mall.domain.PageModel;
import com.hss.mall.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    List<Product> findNewProducts()throws SQLException;

    List<Product> findHotProducts()throws SQLException ;

    public Product findProductByPid(String pid) throws SQLException ;

    List findProductsByCidWithPage(String cid, int startIndex, int pageSize)throws Exception;

    int findTotalRecords(String cid)throws Exception;

    List<Product> findAllProductsWithPage(int startIndex, int pageSize)throws Exception;

    int findTotalRecords()throws Exception;


    void saveProduct(Product product)throws SQLException;

}
