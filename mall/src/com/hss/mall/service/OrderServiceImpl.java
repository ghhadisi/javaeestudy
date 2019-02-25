package com.hss.mall.service;

import com.hss.mall.dao.IOrderDao;
import com.hss.mall.dao.OrderDaoImpl;
import com.hss.mall.domain.Order;
import com.hss.mall.domain.OrderItem;
import com.hss.mall.domain.PageModel;
import com.hss.mall.domain.User;
import com.hss.mall.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements IOrderService {

    IOrderDao orderDao = new OrderDaoImpl();
    @Override
    public void saveOrder(Order order) throws SQLException {
        Connection conn =  null;

        try {
            //获取连接
            conn =  JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //保存订单
            orderDao.saveOrder(conn,order);
            //保存订单项
            for (OrderItem item: order.getList()){
                orderDao.saveOrderItem(conn,item);
            }
            //提交
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();

            conn.rollback();
        }



    }

    @Override
    public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
        int totalRecords=orderDao.getTotalRecords(user);
        PageModel pm=new PageModel(curNum, totalRecords, 3);

        //2_关联集合  select * from orders where uid=? limit ? ,?
        List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
        pm.setRecords(list);
        //3_关联url
        pm.setUrl("orderServlet?method=findMyOrdersWithPage");
        return pm;
    }

    @Override
    public Order findOrderByOid(String oid) throws Exception {
        return orderDao.findOrderByOid(oid);

    }

    @Override
    public void updateOrder(Order order) throws Exception {
        orderDao.updateOrder(order);

    }
}
