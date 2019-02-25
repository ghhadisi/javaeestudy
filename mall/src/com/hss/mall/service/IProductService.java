package com.hss.mall.service;

import com.hss.mall.domain.PageModel;
import com.hss.mall.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {

    List<Product> findNewProducts()throws SQLException;

    List<Product> findHotProducts()throws SQLException ;

    Product findProductByPid(String pid)throws SQLException ;
//


    PageModel findProductsByCidWithPage(String cid, int num) throws Exception;


//
//    void saveProduct(Product product)throws SQLException ;

}
