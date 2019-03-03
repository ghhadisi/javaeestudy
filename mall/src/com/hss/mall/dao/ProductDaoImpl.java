package com.hss.mall.dao;

import com.hss.mall.domain.PageModel;
import com.hss.mall.domain.Product;
import com.hss.mall.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    @Override
    public List<Product> findNewProducts() throws SQLException {

        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where pflag=0 ORDER BY pdate DESC LIMIT 0, 9", new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findHotProducts() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where pflag=0 and is_hot=1 ORDER BY pdate DESC LIMIT 0, 9", new BeanListHandler<Product>(Product.class));
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where pid=?", new BeanHandler<Product>(Product.class),pid);

    }

    @Override
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

        return runner.query("select * from product where cid=? limit ?,?",new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
    }

    @Override
    public int findTotalRecords(String cid) throws Exception {

        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) runner.query("select count(*) from product where cid=?",new ScalarHandler(),cid);
        return num.intValue();
    }

    @Override
    public List<Product> findAllProductsWithPage(int startIndex, int pageSize)throws Exception{
        String sql="select * from product limit  ? , ?";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
    }

    @Override
    public int findTotalRecords() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) runner.query("select count(*) from product ",new ScalarHandler());
        return num.intValue();
    }

    @Override
    public void saveProduct(Product product) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
        runner.update(sql,params);
    }


}
