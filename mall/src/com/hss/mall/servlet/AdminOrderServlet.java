package com.hss.mall.servlet;

import com.hss.mall.domain.Order;
import com.hss.mall.service.IOrderService;
import com.hss.mall.service.OrderServiceImpl;
import com.hss.mall.utils.TextUtils;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminOrderServlet extends BaseServlet {


    public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        IOrderService orderService=new OrderServiceImpl();
        String st=req.getParameter("state");
        List<Order> list=null;

        if (TextUtils.isEmpty(st)){
            list = orderService.findAllOrders();
        }else {
            list = orderService.findAllOrders(st);
        }
        req.setAttribute("allOrders",list);
        return "/admin/order/list.jsp";
    }

    public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //服务端获取到订单ID,
        String oid=req.getParameter("id");
        //查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
        IOrderService OrderService=new OrderServiceImpl();
        Order order=OrderService.findOrderByOid(oid);
        //将返回的集合转换为JSON格式字符串,响应到客户端
        String jsonStr= JSONArray.fromObject(order.getList()).toString();
        //响应到客户端
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(jsonStr);
        return null;
    }
}
