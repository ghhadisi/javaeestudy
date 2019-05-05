package com.hss.mybatis.mapper;

import com.hss.mybatis.bean.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> queryOrderAll();
}
