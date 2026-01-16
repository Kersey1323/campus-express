package com.kersey.service;

import com.kersey.pojo.Order;
import com.kersey.pojo.PageBean;
import com.sun.org.apache.xpath.internal.operations.Or;


import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    public boolean add(Order order);

    public Order selectById(String orderId);

    public List<Order> selectByPage(int begin, int size);

    public int selectCount();

    public boolean deleteById(String orderId);

    public boolean update(Order order);

    public List<Order> selectByConditions(int begin,int size,String condition);

    public int selectCountOfCondition(String condition);

    public int deleteByIds(List<String> ids);

    public int addUserAndOrder(String orderId,int userId);

    public int deleteUserAndOrder(String orderId);

    public int deleteUserAndOrderS(List<String> orderIds);

    public int pay(String orderId);

    public int payByIds(List<String> ids);

    public int orderDelivery(String orderId);

    public int orderDeny(String orderId);

    public int orderCountAdd(int month);
}
