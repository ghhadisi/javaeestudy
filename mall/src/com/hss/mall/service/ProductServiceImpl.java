package com.hss.mall.service;

import com.hss.mall.dao.IProductDao;
import com.hss.mall.dao.ProductDaoImpl;
import com.hss.mall.domain.PageModel;
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

    @Override
    public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {

        //1_创建PageModel对象 目的:计算分页参数
        //统计当前分类下商品个数  select count(*) from product where cid=?
        int total = productDao.findTotalRecords(cid);
        PageModel page = new PageModel(curNum,5,total);
        List<Product> list=   productDao.findProductsByCidWithPage(cid,page.getStartIndex(), page.getPageSize());
        page.setRecords(list);
        page.setUrl("productServlet?method=findProductsWithCidAndPage&cid="+cid);
        return page;
    }
}
